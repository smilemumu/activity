<template>
  <div>
    <a-page-header title="活动详情" @back="() => $router.push('/activities')" />
    <div class="action-bar">
      <a-button @click="$router.push(`/activities/${route.params.id}/edit`)">编辑</a-button>
      <a-space>
        <a-button v-if="detail.status === 'DRAFT'" type="primary" @click="handleStatusAction('PUBLISHED')">发布</a-button>
        <a-popconfirm
          v-if="detail.status === 'PUBLISHED'"
          title="确定要下线该活动吗？"
          ok-text="确定"
          cancel-text="取消"
          @confirm="handleStatusAction('CLOSED')"
        >
          <a-button danger>下线</a-button>
        </a-popconfirm>
        <a-button v-if="detail.status === 'CLOSED'" @click="handleStatusAction('DRAFT')">重新草稿</a-button>
      </a-space>
    </div>
    <a-spin :spinning="loading">
      <a-descriptions title="基础配置" bordered :column="1" style="margin-bottom: 24px">
        <a-descriptions-item label="活动标题">{{ detail.title }}</a-descriptions-item>
        <a-descriptions-item label="活动类型">
          {{ detail.activityType === 'PAGE_REGISTER' ? '页面注册' : '弹窗注册' }}
        </a-descriptions-item>
        <a-descriptions-item label="活动描述">{{ detail.description || '-' }}</a-descriptions-item>
        <a-descriptions-item label="开始时间">{{ detail.startTime }}</a-descriptions-item>
        <a-descriptions-item label="结束时间">{{ detail.endTime }}</a-descriptions-item>
        <a-descriptions-item label="归属机构">
          <a-tag v-for="o in detail.orgRels" :key="o.orgCode">{{ o.orgName }}({{ o.orgCode }})</a-tag>
        </a-descriptions-item>
        <a-descriptions-item label="归属部门">
          <a-tag v-for="d in detail.deptRels" :key="d.deptName">{{ d.deptName }}</a-tag>
        </a-descriptions-item>
        <a-descriptions-item label="状态">
          <a-tag v-if="detail.status === 'DRAFT'" color="default">草稿</a-tag>
          <a-tag v-else-if="detail.status === 'PUBLISHED'" color="green">已发布</a-tag>
          <a-tag v-else color="red">已下线</a-tag>
        </a-descriptions-item>
        <a-descriptions-item label="创建人">{{ detail.createdBy }}</a-descriptions-item>
        <a-descriptions-item label="创建时间">{{ detail.createdTime }}</a-descriptions-item>
      </a-descriptions>

      <a-descriptions title="分享配置" bordered :column="1" style="margin-bottom: 24px">
        <a-descriptions-item label="分享标题">{{ shareConfig?.shareTitle || '-' }}</a-descriptions-item>
        <a-descriptions-item label="分享描述">{{ shareConfig?.shareDesc || '-' }}</a-descriptions-item>
      </a-descriptions>
    </a-spin>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { message } from 'ant-design-vue'
import { getActivityDetail, updateActivityStatus } from '../api/activity'
import { getShareConfig } from '../api/shareConfig'
import { getRewards } from '../api/reward'

const route = useRoute()
const loading = ref(false)
const detail = ref({})
const shareConfig = ref(null)
const rewards = ref([])

async function fetchDetail() {
  const [dRes, sRes, rRes] = await Promise.all([
    getActivityDetail(route.params.id),
    getShareConfig(route.params.id),
    getRewards(route.params.id),
  ])
  detail.value = dRes.data
  shareConfig.value = sRes.data
  rewards.value = rRes.data
}

async function handleStatusAction(status) {
  try {
    await updateActivityStatus(route.params.id, status)
    message.success('操作成功')
    await fetchDetail()
  } catch {
    message.error('操作失败')
  }
}

onMounted(async () => {
  loading.value = true
  try {
    await fetchDetail()
  } finally {
    loading.value = false
  }
})
</script>

<style scoped>
.action-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}
</style>
