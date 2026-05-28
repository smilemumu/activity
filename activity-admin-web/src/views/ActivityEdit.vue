<template>
  <div>
    <a-page-header :title="isEdit ? '编辑活动' : '新建活动'" @back="() => $router.push('/activities')" />
    <a-steps :current="step" style="max-width: 900px; margin: 24px auto">
      <a-step title="基础配置" />
      <a-step title="页面配置" />
      <a-step title="规则配置" />
      <a-step title="分享设置" />
      <a-step title="完成" />
    </a-steps>

    <div class="step-content">
      <!-- 步骤1: 基础配置 -->
      <div v-show="step === 0">
        <a-form :model="basic" :rules="basicRules" ref="basicForm" layout="vertical" style="max-width: 700px; margin: 0 auto">
          <a-form-item label="活动类型" name="activityType">
            <a-radio-group v-model:value="basic.activityType">
              <a-radio value="PAGE_REGISTER">页面注册</a-radio>
              <a-radio value="POPUP_REGISTER">弹窗注册</a-radio>
            </a-radio-group>
          </a-form-item>

          <a-form-item label="活动标题" name="title">
            <a-input v-model:value="basic.title" placeholder="给活动起个名字" :maxlength="100" />
          </a-form-item>

          <a-form-item label="活动描述">
            <a-textarea v-model:value="basic.description" placeholder="简单介绍此活动，不在活动页面展示" :rows="3" :maxlength="500" />
          </a-form-item>

          <a-form-item label="活动时间" name="timeRange">
            <a-range-picker v-model:value="basic.timeRange" show-time style="width: 100%" />
          </a-form-item>

          <a-form-item label="归属机构">
            <a-select v-model:value="basic.orgCodes" mode="multiple" placeholder="请选择归属机构">
              <a-select-option v-for="o in orgList" :key="o.orgCode" :value="o.orgCode">{{ o.orgName }}</a-select-option>
            </a-select>
          </a-form-item>

          <a-form-item label="归属部门">
            <a-select v-model:value="basic.deptNames" mode="multiple" placeholder="请选择归属部门">
              <a-select-option v-for="d in deptList" :key="d.deptName" :value="d.deptName">{{ d.deptName }}（{{ d.contactName }}）</a-select-option>
            </a-select>
          </a-form-item>

          <a-form-item label="活动封面">
            <a-upload
              v-model:file-list="coverFileList"
              list-type="picture-card"
              :max-count="1"
              accept="image/*"
              action="/api/common/upload"
              name="file"
              :headers="uploadHeaders"
              @change="onCoverChange"
            >
              <div v-if="coverFileList.length < 1">
                <plus-outlined />
                <div style="margin-top: 8px">上传封面</div>
              </div>
            </a-upload>
            <div style="color: #999; font-size: 12px">建议尺寸 750×1440px</div>
          </a-form-item>
        </a-form>
      </div>

      <!-- 步骤2: 页面配置 -->
      <div v-show="step === 1">
        <a-row :gutter="24">
          <a-col :span="6">
            <div class="config-panel">
              <h4>可调整项</h4>
              <a-tree :tree-data="pageTreeData" :selected-keys="pageSelectedKey" @select="onPageSelect" />
            </div>
          </a-col>
          <a-col :span="12">
            <div class="config-panel">
              <div class="config-nav">
                <a-button size="small" @click="prevPageItem">上一项</a-button>
                <span style="margin: '0 8px'">{{ currentPageItem }}</span>
                <a-button size="small" @click="nextPageItem">下一项</a-button>
              </div>
              <div v-if="currentPageItem === '背景图上传'" style="margin-top: 16px">
                <a-upload
                  v-model:file-list="bgFileList"
                  list-type="picture-card"
                  :max-count="1"
                  :before-upload="() => false"
                >
                  <plus-outlined />
                </a-upload>
              </div>
              <div v-else-if="currentPageItem === '按钮文案'" style="margin-top: 16px">
                <a-input v-model:value="pageBtnText" placeholder="请输入按钮文案" />
              </div>
              <div v-else-if="currentPageItem === '活动规则'" style="margin-top: 16px">
                <a-textarea v-model:value="pageRuleText" :rows="6" placeholder="请输入活动规则文本" />
              </div>
              <div v-else style="margin-top: 16px; color: #999">
                请在左侧选择要调整的项目
              </div>
            </div>
          </a-col>
          <a-col :span="6">
            <div class="config-panel">
              <h4>画布尺寸</h4>
              <a-input-group>
                <a-input v-model:value="canvasWidth" addon-before="宽" addon-after="px" style="margin-bottom: 8px" />
                <a-input v-model:value="canvasHeight" addon-before="高" addon-after="px" />
              </a-input-group>
            </div>
          </a-col>
        </a-row>
      </div>

      <!-- 步骤3: 规则配置 -->
      <div v-show="step === 2" style="max-width: 700px; margin: 0 auto">
        <a-collapse v-model:activeKey="ruleActiveKeys">
          <a-collapse-panel key="base" header="规则基础配置">
            <a-form layout="vertical">
              <a-form-item label="是否有奖励">
                <a-switch v-model:checked="rule.hasReward" checked-children="开" un-checked-children="关" />
              </a-form-item>
              <a-form-item v-if="rule.hasReward" label="奖励发放机制">
                <a-select v-model:value="rule.rewardDeliveryType" placeholder="请选择">
                  <a-select-option value="AUTO">自动发放</a-select-option>
                  <a-select-option value="MANUAL">自己领取</a-select-option>
                </a-select>
              </a-form-item>
              <a-form-item label="授信/用款产品编码">
                <a-select v-model:value="rule.productCodes" mode="multiple" placeholder="选择产品">
                  <a-select-option v-for="p in productList" :key="p.productCode" :value="p.productCode">{{ p.productName }}（{{ p.productCode }}）</a-select-option>
                </a-select>
              </a-form-item>
              <a-form-item label="活动有效期内奖励总领取次数">
                <a-switch v-model:checked="rule.totalClaimLimitEnabled" />&nbsp;
                <a-input-number v-if="rule.totalClaimLimitEnabled" v-model:value="rule.totalClaimLimit" :min="1" />
              </a-form-item>
              <a-form-item label="活动地域控制">
                <a-switch v-model:checked="rule.regionLimitEnabled" />
              </a-form-item>
              <a-form-item label="奖励互斥活动">
                <a-switch v-model:checked="rule.mutualExclusiveEnabled" />
              </a-form-item>
            </a-form>
          </a-collapse-panel>

          <a-collapse-panel key="condition" header="参与条件配置">
            <a-radio-group v-model:value="condition.type">
              <a-radio value="ALL">所有客户（未注册/已注册）</a-radio>
              <a-radio value="CREDIT_SUCCESS">有授信成功记录客户</a-radio>
              <a-radio value="LOAN_SUCCESS">有用款成功记录客户（已授信）</a-radio>
            </a-radio-group>
            <div v-if="condition.type !== 'ALL'" style="margin-top: 12px">
              <a-select v-model:value="condition.productFilter" mode="multiple" placeholder="筛选产品" style="width: 100%">
                <a-select-option v-for="p in productList" :key="p.productCode" :value="p.productCode">{{ p.productName }}</a-select-option>
              </a-select>
            </div>
          </a-collapse-panel>

          <a-collapse-panel key="reward" header="奖励及奖励条件配置">
            <div v-if="!rule.hasReward" style="color: #999">请先在规则基础配置中开启"是否有奖励"</div>
            <template v-else>
              <a-button type="dashed" block @click="addRewardItem" style="margin-bottom: 12px">
                <plus-outlined /> 添加奖励
              </a-button>
              <a-card v-for="(reward, idx) in rewardList" :key="idx" size="small" style="margin-bottom: 12px">
                <template #title>{{ reward.rewardName || '新奖励' }}</template>
                <template #extra>
                  <a-button type="link" danger @click="removeRewardItem(idx)">删除</a-button>
                </template>
                <a-form layout="vertical">
                  <a-form-item label="奖励类型">
                    <a-select v-model:value="reward.rewardType">
                      <a-select-option value="POINTS">积分</a-select-option>
                      <a-select-option value="COUPON">优惠券</a-select-option>
                      <a-select-option value="EXCHANGE_CODE">兑换码</a-select-option>
                      <a-select-option value="THANKS">谢谢参与</a-select-option>
                      <a-select-option value="MALL_COUPON">商城商品券</a-select-option>
                    </a-select>
                  </a-form-item>
                  <a-form-item label="奖励编码" required>
                    <a-input v-model:value="reward.rewardCode" placeholder="请输入奖励编码" />
                  </a-form-item>
                  <a-form-item label="奖励名称">
                    <a-input v-model:value="reward.rewardName" />
                  </a-form-item>
                  <a-form-item label="奖励库存">
                    <a-input-number v-model:value="reward.stock" :min="0" addon-after="个" />
                  </a-form-item>
                  <a-form-item label="最大领取次数">
                    <a-input-number v-model:value="reward.maxClaimTimes" :min="1" />
                  </a-form-item>
                  <a-form-item label="奖励图标">
                    <a-switch v-model:checked="reward.hasIcon" />
                  </a-form-item>
                  <a-form-item label="奖励别名">
                    <a-switch v-model:checked="reward.hasAlias" />&nbsp;
                    <a-input v-if="reward.hasAlias" v-model:value="reward.rewardAlias" style="width: 200px" />
                  </a-form-item>
                </a-form>
              </a-card>
            </template>
          </a-collapse-panel>
        </a-collapse>
      </div>

      <!-- 步骤4: 分享设置 -->
      <div v-show="step === 3" style="max-width: 500px; margin: 0 auto">
        <a-form layout="vertical">
          <a-form-item label="分享标题">
            <a-input v-model:value="share.shareTitle" placeholder="微信分享标题" :maxlength="20" />
            <span style="color: #999; font-size: 12px">{{ (share.shareTitle || '').length }}/10字</span>
          </a-form-item>
          <a-form-item label="分享描述">
            <a-textarea v-model:value="share.shareDesc" placeholder="微信分享描述" :rows="2" :maxlength="60" />
            <span style="color: #999; font-size: 12px">{{ (share.shareDesc || '').length }}/30字</span>
          </a-form-item>
          <a-form-item label="分享图标（200×200px）">
            <a-upload
              v-model:file-list="shareIconList"
              list-type="picture-card"
              :max-count="1"
              accept="image/*"
              action="/api/common/upload"
              name="file"
              :headers="uploadHeaders"
              @change="onShareIconChange"
            >
              <plus-outlined />
            </a-upload>
          </a-form-item>
        </a-form>
      </div>

      <!-- 步骤5: 完成预览 -->
      <div v-show="step === 4" style="max-width: 700px; margin: 0 auto">
        <a-descriptions title="活动配置概览" bordered :column="1">
          <a-descriptions-item label="活动类型">{{ basic.activityType === 'PAGE_REGISTER' ? '页面注册' : '弹窗注册' }}</a-descriptions-item>
          <a-descriptions-item label="活动标题">{{ basic.title }}</a-descriptions-item>
          <a-descriptions-item label="活动描述">{{ basic.description || '-' }}</a-descriptions-item>
          <a-descriptions-item label="活动时间">{{ basic.timeRange?.[0]?.format('YYYY-MM-DD HH:mm') }} ~ {{ basic.timeRange?.[1]?.format('YYYY-MM-DD HH:mm') }}</a-descriptions-item>
          <a-descriptions-item label="奖励数量">{{ rewardList.length }} 项</a-descriptions-item>
          <a-descriptions-item label="分享标题">{{ share.shareTitle || '-' }}</a-descriptions-item>
        </a-descriptions>
        <div style="text-align: center; margin-top: 24px">
          <a-space>
            <a-button type="primary">预览二维码</a-button>
            <a-button>预览链接</a-button>
          </a-space>
        </div>
      </div>
    </div>

    <div class="step-actions">
      <a-button v-if="step > 0" @click="step--">上一步</a-button>
      <a-button v-if="step < 4" type="primary" @click="handleNext">下一步</a-button>
      <a-button v-if="step === 4" type="primary" @click="handleSubmit" :loading="submitting">
        {{ isEdit ? '保存修改' : '创建活动' }}
      </a-button>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { message } from 'ant-design-vue'
import { PlusOutlined } from '@ant-design/icons-vue'
import dayjs from 'dayjs'
import { createActivity, updateActivity, getActivityDetail } from '../api/activity'
import { getDepartments, getProducts, getOrganizations } from '../api/dict'
import { savePageConfig } from '../api/pageConfig'
import { saveRuleConfig, getRuleConfig } from '../api/ruleConfig'
import { saveShareConfig, getShareConfig } from '../api/shareConfig'
import { getRewards, addReward, updateReward, deleteReward } from '../api/reward'

const router = useRouter()
const route = useRoute()
const isEdit = !!route.params.id
const step = ref(0)
const submitting = ref(false)

const basic = reactive({
  activityType: 'PAGE_REGISTER',
  title: '',
  description: '',
  timeRange: null,
  orgCodes: [],
  deptNames: [],
})

const orgList = ref([])
const deptList = ref([])
const productList = ref([])
const coverFileList = ref([])
const uploadHeaders = { Authorization: `Bearer ${localStorage.getItem('token') || ''}` }

const canvasWidth = ref(750)
const canvasHeight = ref(1440)
const pageBtnText = ref('立即参与')
const pageRuleText = ref('')

const pageSelectedKey = ref([])
const currentPageItem = ref('')
const bgFileList = ref([])

const rule = reactive({
  hasReward: false,
  rewardDeliveryType: 'AUTO',
  productCodes: [],
  totalClaimLimitEnabled: false,
  totalClaimLimit: 1,
  regionLimitEnabled: false,
  mutualExclusiveEnabled: false,
})

const condition = reactive({
  type: 'ALL',
  productFilter: [],
})

const rewardList = ref([])
const ruleActiveKeys = ref(['base', 'condition', 'reward'])

const share = reactive({ shareTitle: '', shareDesc: '' })
const shareIconList = ref([])

const pageTreeData = [
  {
    title: '页面',
    key: 'page',
    children: [
      { title: '首页-带图形验证码', key: 'home-with-captcha',
        children: [
          { title: '头部背景-图片替换', key: 'header-bg' },
          { title: '中间按钮文案', key: 'btn-text' },
          { title: '尾部背景-图片替换', key: 'footer-bg' },
        ] },
      { title: '首页-不带图形验证码', key: 'home-no-captcha' },
    ],
  },
  {
    title: '弹窗',
    key: 'popup',
    children: [
      { title: '活动规则', key: 'rule-text' },
      { title: '注册协议', key: 'agreement' },
      { title: '隐私政策', key: 'privacy' },
      { title: '登录注册成功', key: 'success-dialog' },
      { title: '登录注册失败', key: 'fail-dialog' },
    ],
  },
]

onMounted(async () => {
  const [orgRes, deptRes, prodRes] = await Promise.all([
    getOrganizations(), getDepartments(), getProducts(),
  ])
  orgList.value = orgRes.data
  deptList.value = deptRes.data
  productList.value = prodRes.data

  if (isEdit) {
    const res = await getActivityDetail(route.params.id)
    const d = res.data
    basic.activityType = d.activityType
    basic.title = d.title
    basic.description = d.description || ''
    basic.timeRange = d.startTime && d.endTime ? [dayjs(d.startTime), dayjs(d.endTime)] : null
    basic.orgCodes = (d.orgRels || []).map(r => r.orgCode)
    basic.deptNames = (d.deptRels || []).map(r => r.deptName)
    if (d.coverImage) {
      coverFileList.value = [{ uid: '-1', name: 'cover', status: 'done', url: d.coverImage }]
    }

    try {
      const shareRes = await getShareConfig(route.params.id)
      if (shareRes.data) {
        share.shareTitle = shareRes.data.shareTitle || ''
        share.shareDesc = shareRes.data.shareDesc || ''
        if (shareRes.data.shareIcon) {
          shareIconList.value = [{ uid: '-1', name: 'share-icon', status: 'done', url: shareRes.data.shareIcon }]
        }
      }
    } catch { /* 暂无分享配置 */ }

    try {
      const ruleRes = await getRuleConfig(route.params.id)
      if (ruleRes.data) {
        const rc = ruleRes.data.ruleConfig
        if (rc) {
          rule.hasReward = rc.hasReward === 'Y'
          rule.rewardDeliveryType = rc.rewardDeliveryType || 'AUTO'
          rule.totalClaimLimitEnabled = rc.totalClaimLimitEnabled === 'Y'
          rule.totalClaimLimit = rc.totalClaimLimit || 1
          rule.regionLimitEnabled = rc.regionLimitEnabled === 'Y'
          rule.mutualExclusiveEnabled = rc.mutualExclusiveEnabled === 'Y'
        }
        if (ruleRes.data.products) {
          rule.productCodes = ruleRes.data.products.map(p => p.productCode)
        }
        if (ruleRes.data.conditions && ruleRes.data.conditions.length > 0) {
          const c = ruleRes.data.conditions[0]
          condition.type = c.conditionType || 'ALL'
          try { condition.productFilter = JSON.parse(c.productFilter || '[]') } catch { condition.productFilter = [] }
        }
      }
    } catch { /* 暂无规则配置 */ }

    try {
      const rewardRes = await getRewards(route.params.id)
      if (rewardRes.data && rewardRes.data.length > 0) {
        rewardList.value = rewardRes.data.map(r => ({
          id: r.id,
          rewardType: r.rewardType,
          rewardCode: r.rewardCode || '',
          rewardName: r.rewardName || '',
          stock: r.stock || 0,
          maxClaimTimes: r.maxClaimTimes || 1,
          hasIcon: !!r.rewardIcon,
          hasAlias: !!r.rewardAlias,
          rewardAlias: r.rewardAlias || '',
        }))
      }
    } catch { /* 暂无奖励配置 */ }
  }
})

function onCoverChange({ file, fileList }) {
  if (file.status === 'done' && file.response) {
    file.url = file.response.data
  }
  coverFileList.value = [...fileList]
}

function onShareIconChange({ file, fileList }) {
  if (file.status === 'done' && file.response) {
    file.url = file.response.data
  }
  shareIconList.value = [...fileList]
}

function onPageSelect(keys) {
  pageSelectedKey.value = keys
  currentPageItem.value = keys[0] || ''
}

function prevPageItem() {}
function nextPageItem() {}

function addRewardItem() {
  rewardList.value.push({
    id: null,
    rewardType: 'POINTS',
    rewardCode: '',
    rewardName: '',
    stock: 0,
    maxClaimTimes: 1,
    hasIcon: false,
    hasAlias: false,
    rewardAlias: '',
  })
}

function removeRewardItem(idx) {
  rewardList.value.splice(idx, 1)
}

async function handleNext() {
  if (step.value === 0) {
    if (!basic.title) { message.warning('请输入活动标题'); return }
    if (!basic.timeRange) { message.warning('请选择活动时间'); return }
  }
  step.value++
}

async function handleSubmit() {
  submitting.value = true
  try {
    const payload = {
      activityType: basic.activityType,
      title: basic.title,
      description: basic.description,
      startTime: basic.timeRange?.[0]?.toISOString(),
      endTime: basic.timeRange?.[1]?.toISOString(),
      coverImage: coverFileList.value[0]?.response?.data || coverFileList.value[0]?.url || '',
      orgRels: basic.orgCodes.map(c => {
        const org = orgList.value.find(o => o.orgCode === c)
        return { orgCode: c, orgName: org?.orgName || '' }
      }),
      deptRels: basic.deptNames.map(d => ({ deptName: d, contactName: '' })),
    }

    let id
    if (isEdit) {
      id = route.params.id
      await updateActivity(id, payload)
    } else {
      const res = await createActivity(payload)
      id = res.data
    }

    // 保存页面配置
    const pageConfigs = [
      { configType: 'BG_IMAGE', configKey: 'bg', configValue: bgFileList.value[0]?.url || '', width: canvasWidth.value, height: canvasHeight.value },
      { configType: 'BTN_TEXT', configKey: 'btn', configValue: pageBtnText.value },
      { configType: 'RULE_TEXT', configKey: 'rule', configValue: pageRuleText.value },
    ]
    await savePageConfig(id, pageConfigs)

    // 保存规则配置
    await saveRuleConfig(id, {
      ruleConfig: {
        hasReward: rule.hasReward ? 'Y' : 'N',
        rewardDeliveryType: rule.rewardDeliveryType,
        ruleType: 'REGISTER',
        totalClaimLimitEnabled: rule.totalClaimLimitEnabled ? 'Y' : 'N',
        totalClaimLimit: rule.totalClaimLimit,
        regionLimitEnabled: rule.regionLimitEnabled ? 'Y' : 'N',
        mutualExclusiveEnabled: rule.mutualExclusiveEnabled ? 'Y' : 'N',
      },
      products: rule.productCodes.map(c => {
        const p = productList.value.find(p => p.productCode === c)
        return { productCode: c, productName: p?.productName || '' }
      }),
      conditions: [{ conditionType: condition.type, productFilter: JSON.stringify(condition.productFilter) }],
    })

    // 同步奖励配置
    const existingRewards = await getRewards(id).then(r => r.data || []).catch(() => [])
    const existingIds = existingRewards.map(r => r.id)
    const keepIds = rewardList.value.filter(r => r.id).map(r => r.id)
    // 删除已移除的奖励
    for (const rid of existingIds) {
      if (!keepIds.includes(rid)) {
        await deleteReward(id, rid).catch(() => {})
      }
    }
    // 新建或更新奖励
    for (const reward of rewardList.value) {
      const body = {
        rewardType: reward.rewardType,
        rewardCode: reward.rewardCode,
        rewardName: reward.rewardName,
        rewardAlias: reward.hasAlias ? reward.rewardAlias : '',
        rewardIcon: reward.hasIcon ? (reward.rewardIconUrl || '') : '',
        stock: reward.stock,
        maxClaimTimes: reward.maxClaimTimes,
        occupyWinCount: 'Y',
      }
      if (reward.id) {
        await updateReward(id, reward.id, body)
      } else {
        await addReward(id, body)
      }
    }

    // 保存分享配置
    await saveShareConfig(id, {
      shareTitle: share.shareTitle,
      shareDesc: share.shareDesc,
      shareIcon: shareIconList.value[0]?.response?.data || shareIconList.value[0]?.url || '',
    })

    message.success(isEdit ? '保存成功' : '创建成功')
    router.push('/activities')
  } catch (e) {
    message.error('保存失败')
  } finally {
    submitting.value = false
  }
}
</script>

<style scoped>
.step-content {
  max-width: 1100px;
  margin: 24px auto;
  min-height: 400px;
}

.config-panel {
  background: #fafafa;
  border: 1px solid #e8e8e8;
  border-radius: 4px;
  padding: 16px;
  min-height: 300px;
}

.config-panel h4 {
  margin: 0 0 12px;
}

.config-nav {
  display: flex;
  align-items: center;
  gap: 8px;
  justify-content: center;
}

.step-actions {
  text-align: center;
  padding: 16px 0;
  display: flex;
  gap: 12px;
  justify-content: center;
}
</style>
