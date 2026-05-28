<template>
  <div>
    <h2>数据看板</h2>
    <a-divider />

    <a-select v-model:value="selectedActivityId" placeholder="选择活动" style="width: 300px; margin-bottom: 24px" @change="onActivityChange">
      <a-select-option v-for="a in activities" :key="a.id" :value="a.id">{{ a.title }}</a-select-option>
    </a-select>

    <a-row :gutter="16" v-if="selectedActivityId" style="display: flex">
      <a-col style="flex: 1; min-width: 0">
        <a-statistic title="页面点击量 (PV)" :value="pvUvData.pv || 0" />
      </a-col>
      <a-col style="flex: 1; min-width: 0">
        <a-statistic title="访问客户数 (UV)" :value="pvUvData.uv || 0" />
      </a-col>
      <a-col style="flex: 1; min-width: 0">
        <a-statistic title="奖励领取数量" :value="claimCountData" />
      </a-col>
      <a-col style="flex: 1; min-width: 0">
        <a-statistic title="奖励总库存" :value="stockData.totalStock || 0" />
      </a-col>
      <a-col style="flex: 1; min-width: 0">
        <a-statistic title="剩余库存" :value="stockData.remainingStock || 0" />
      </a-col>
    </a-row>

    <a-divider v-if="selectedActivityId" />

    <div v-if="selectedActivityId">
      <h3>奖励库存明细</h3>
      <a-table :columns="stockColumns" :data-source="stockData.items || []" row-key="rewardName" size="small" style="margin-bottom: 24px">
        <template #bodyCell="{ column, index }">
          <template v-if="column.key === 'index'">{{ index + 1 }}</template>
        </template>
      </a-table>

      <h3>奖励领取记录</h3>
      <div style="display: flex; gap: 12px; margin-bottom: 12px; flex-wrap: wrap">
        <a-input v-model:value="searchRewardCode" placeholder="奖励码" style="width: 160px" allow-clear @pressEnter="fetchRecords" />
        <a-input v-model:value="searchPhone" placeholder="手机号" style="width: 160px" allow-clear @pressEnter="fetchRecords" />
        <a-input v-model:value="searchCustomerId" placeholder="客户ID" style="width: 160px" allow-clear @pressEnter="fetchRecords" />
        <a-button type="primary" @click="fetchRecords">搜索</a-button>
      </div>
      <a-table
        :columns="recordColumns"
        :data-source="records"
        :loading="recordLoading"
        :pagination="recordPagination"
        row-key="id"
        @change="onRecordPageChange"
      >
        <template #bodyCell="{ column, index }">
          <template v-if="column.key === 'index'">{{ (recordPagination.current - 1) * recordPagination.pageSize + index + 1 }}</template>
        </template>
      </a-table>
    </div>

    <a-empty v-else description="请选择一个活动查看数据" />
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { getActivityPage } from '../api/activity'
import { getPvUv, getRewardStock, getRewardClaimCount, getClaimRecords } from '../api/report'

const activities = ref([])
const selectedActivityId = ref(null)
const pvUvData = ref({})
const claimCountData = ref(0)
const stockData = ref({})

const searchRewardCode = ref('')
const searchPhone = ref('')
const searchCustomerId = ref('')
const records = ref([])
const recordLoading = ref(false)
const recordPagination = reactive({ current: 1, pageSize: 10, total: 0, showSizeChanger: true, pageSizeOptions: ['10', '20', '100'] })

const stockColumns = [
  { title: '序号', key: 'index', width: 60 },
  { title: '奖励码', dataIndex: 'rewardCode', key: 'rewardCode' },
  { title: '奖励名称', dataIndex: 'rewardName', key: 'rewardName' },
  { title: '类型', dataIndex: 'rewardType', key: 'rewardType' },
  { title: '总库存', dataIndex: 'totalStock', key: 'totalStock' },
  { title: '剩余库存', dataIndex: 'remainingStock', key: 'remainingStock' },
]

const recordColumns = [
  { title: '序号', key: 'index', width: 60 },
  { title: '奖励码', dataIndex: 'rewardCode', key: 'rewardCode' },
  { title: '手机号', dataIndex: 'phone', key: 'phone' },
  { title: '客户ID', dataIndex: 'customerId', key: 'customerId' },
  { title: '奖励名称', dataIndex: 'rewardName', key: 'rewardName' },
  { title: '领取时间', dataIndex: 'claimTime', key: 'claimTime' },
]

onMounted(async () => {
  const res = await getActivityPage({ page: 1, size: 50 })
  activities.value = res.data.list
})

async function onActivityChange(id) {
  const [pvRes, claimRes, stockRes] = await Promise.all([
    getPvUv(id), getRewardClaimCount(id), getRewardStock(id),
  ])
  pvUvData.value = pvRes.data
  claimCountData.value = claimRes.data.count || 0
  stockData.value = stockRes.data
  fetchRecords()
}

async function fetchRecords() {
  if (!selectedActivityId.value) return
  recordLoading.value = true
  try {
    const res = await getClaimRecords({
      activityId: selectedActivityId.value,
      rewardCode: searchRewardCode.value || undefined,
      phone: searchPhone.value || undefined,
      customerId: searchCustomerId.value || undefined,
      page: recordPagination.current,
      size: recordPagination.pageSize,
    })
    records.value = res.data.list
    recordPagination.total = res.data.total
  } finally {
    recordLoading.value = false
  }
}

function onRecordPageChange(pag) {
  recordPagination.current = pag.current
  recordPagination.pageSize = pag.pageSize
  fetchRecords()
}
</script>
