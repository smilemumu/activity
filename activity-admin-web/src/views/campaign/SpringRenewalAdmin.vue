<template>
  <!--
    春日焕新活动 - 后台管理页面
    用于在 admin 系统中配置和管理春日焕新具体活动
  -->
  <div class="spring-admin">
    <a-page-header title="春日焕新活动" sub-title="Spring Renewal Campaign" @back="() => $router.back()" />

    <a-tabs v-model:activeKey="activeTab">
      <!-- Tab 1: 活动概览 -->
      <a-tab-pane key="overview" tab="活动概览">
        <a-row :gutter="16">
          <a-col :span="8">
            <a-card title="参与数据">
              <a-statistic title="累计参与人数" :value="stats.totalUsers" suffix="人" />
              <a-statistic title="实名认证通过" :value="stats.authUsers" suffix="人" />
              <a-statistic title="首借转化率" :value="stats.conversionRate" suffix="%" />
            </a-card>
          </a-col>
          <a-col :span="8">
            <a-card title="奖励发放">
              <a-statistic title="已发放奖励" :value="stats.totalRewards" suffix="份" />
              <a-statistic title="待发放" :value="stats.pendingRewards" suffix="份" />
            </a-card>
          </a-col>
          <a-col :span="8">
            <a-card title="邀请数据">
              <a-statistic title="邀请总人数" :value="stats.totalInvites" suffix="人" />
              <a-statistic title="裂变层级" :value="stats.maxInviteLevel" suffix="级" />
            </a-card>
          </a-col>
        </a-row>
      </a-tab-pane>

      <!-- Tab 2: 页面配置 -->
      <a-tab-pane key="page-config" tab="页面配置">
        <a-card title="H5 页面配置">
          <a-form layout="vertical">
            <a-form-item label="主标题">
              <a-input v-model:value="pageConfig.mainTitle" placeholder="春日焕新" />
            </a-form-item>
            <a-form-item label="副标题">
              <a-input v-model:value="pageConfig.subTitle" placeholder="诚e贷新用户专享礼遇" />
            </a-form-item>
            <a-form-item label="奖励金额（元）">
              <a-input-number v-model:value="pageConfig.prizeAmount" :min="0" />
            </a-form-item>
            <a-form-item label="Banner 背景色">
              <a-input type="color" v-model:value="pageConfig.bannerColor" />
            </a-form-item>
            <a-form-item label="Banner 图片">
              <a-upload action="/api/common/upload" :max-count="1">
                <a-button>上传 Banner</a-button>
              </a-upload>
            </a-form-item>
            <a-form-item>
              <a-button type="primary" @click="savePageConfig">保存配置</a-button>
              <a-button style="margin-left: 8px" @click="previewPage">预览页面</a-button>
            </a-form-item>
          </a-form>
        </a-card>
      </a-tab-pane>

      <!-- Tab 3: 奖励阶段管理 -->
      <a-tab-pane key="reward-stages" tab="奖励管理">
        <a-card title="奖励阶段配置">
          <a-table :columns="rewardColumns" :dataSource="rewardStages" rowKey="stageCode" :pagination="false">
            <template #bodyCell="{ column, record }">
              <template v-if="column.key === 'action'">
                <a-button size="small" @click="editReward(record)">编辑</a-button>
              </template>
            </template>
          </a-table>
        </a-card>
      </a-tab-pane>

      <!-- Tab 4: 参与记录 -->
      <a-tab-pane key="records" tab="参与记录">
        <a-card title="用户参与记录">
          <a-table :columns="recordColumns" :dataSource="userRecords" rowKey="id">
            <template #bodyCell="{ column, record }">
              <template v-if="column.key === 'status'">
                <a-tag :color="record.status === 'SUCCESS' ? 'green' : record.status === 'PENDING' ? 'orange' : 'red'">
                  {{ record.status }}
                </a-tag>
              </template>
              <template v-if="column.key === 'action'">
                <a-button size="small" @click="viewDetail(record)">详情</a-button>
              </template>
            </template>
          </a-table>
          <a-pagination
            v-model:current="pagination.page"
            :total="pagination.total"
            :pageSize="pagination.size"
            show-size-changer
            @change="fetchRecords"
          />
        </a-card>
      </a-tab-pane>
    </a-tabs>

    <!-- H5 页面预览抽屉 -->
    <a-drawer
      title="H5 页面预览"
      :width="400"
      :open="previewVisible"
      @close="previewVisible = false"
    >
      <iframe
        v-if="previewVisible"
        :src="`/campaign/chunrihuanxin/${props.activityId}`"
        style="width: 375px; height: 100%; border: none; margin: 0 auto; display: block;"
      />
    </a-drawer>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { message } from 'ant-design-vue'

const props = defineProps({ activityId: { type: Number, required: true } })
const router = useRouter()
const activeTab = ref('overview')
const previewVisible = ref(false)

// 统计数据
const stats = reactive({
  totalUsers: 0,
  authUsers: 0,
  conversionRate: 0,
  totalRewards: 0,
  pendingRewards: 0,
  totalInvites: 0,
  maxInviteLevel: 0
})

// 页面配置
const pageConfig = reactive({
  mainTitle: '春日焕新',
  subTitle: '诚e贷新用户专享礼遇',
  prizeAmount: 888,
  bannerColor: '#FFECD2'
})

// 奖励列表
const rewardColumns = [
  { title: '阶段编码', dataIndex: 'stageCode', key: 'stageCode' },
  { title: '阶段名称', dataIndex: 'stageName', key: 'stageName' },
  { title: '奖励类型', dataIndex: 'rewardType', key: 'rewardType' },
  { title: '奖励内容', dataIndex: 'rewardValue', key: 'rewardValue' },
  { title: '触发条件', dataIndex: 'condition', key: 'condition' },
  { title: '操作', key: 'action', width: 80 }
]

const rewardStages = ref([
  { stageCode: 'REGISTER', stageName: '注册奖励', rewardType: 'POINTS', rewardValue: '积分 100', condition: '完成注册' },
  { stageCode: 'REAL_NAME', stageName: '实名认证奖励', rewardType: 'COUPON', rewardValue: '优惠券 30元', condition: '完成实名认证' },
  { stageCode: 'FIRST_BORROW', stageName: '首借奖励', rewardType: 'COUPON', rewardValue: '返现 50元', condition: '首次借款≥1000元' },
  { stageCode: 'CREDIT_LIMIT', stageName: '额度奖励', rewardType: 'MALL_COUPON', rewardValue: '提额券 200元', condition: '授信额度≥5000元' },
  { stageCode: 'LADDER', stageName: '阶梯奖励', rewardType: 'EXCHANGE_CODE', rewardValue: '京东卡 100元', condition: '累计借款≥10000元' }
])

// 参与记录
const recordColumns = [
  { title: '用户姓名', dataIndex: 'realName', key: 'realName' },
  { title: '手机号', dataIndex: 'phone', key: 'phone' },
  { title: '参与时间', dataIndex: 'createTime', key: 'createTime' },
  { title: '状态', key: 'status', dataIndex: 'status' },
  { title: '操作', key: 'action', width: 80 }
]

const userRecords = ref([])
const pagination = reactive({ page: 1, size: 10, total: 0 })

async function savePageConfig() { message.success('保存成功') }
function previewPage() { previewVisible.value = true }
function editReward(record) { message.info(`编辑 ${record.stageName}`) }
function viewDetail(record) { message.info(`查看 ${record.realName} 详情`) }
function fetchRecords() { /* 调用后端接口 */ }
</script>
