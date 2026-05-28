<template>
  <div>
    <div class="toolbar">
      <a-space>
        <a-input-search v-model:value="keyword" placeholder="搜索活动标题" style="width: 240px" @search="onSearch" />
        <a-select v-model:value="status" placeholder="状态筛选" style="width: 120px" allow-clear @change="onSearch">
          <a-select-option value="">全部</a-select-option>
          <a-select-option value="DRAFT">草稿</a-select-option>
          <a-select-option value="PUBLISHED">已发布</a-select-option>
          <a-select-option value="CLOSED">已下线</a-select-option>
        </a-select>
      </a-space>
      <a-button type="primary" @click="$router.push('/activities/create')">
        <plus-outlined /> 新建活动
      </a-button>
    </div>

    <a-table
      :columns="columns"
      :data-source="dataSource"
      :loading="loading"
      :pagination="pagination"
      row-key="id"
      @change="onTableChange"
    >
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'status'">
          <a-tag v-if="record.status === 'DRAFT'" color="default">草稿</a-tag>
          <a-tag v-else-if="record.status === 'PUBLISHED'" color="green">已发布</a-tag>
          <a-tag v-else-if="record.status === 'CLOSED'" color="red">已下线</a-tag>
        </template>
        <template v-if="column.key === 'action'">
          <a-space>
            <a @click="$router.push(`/activities/${record.id}/edit`)">编辑</a>
            <a @click="$router.push(`/activities/${record.id}/detail`)">详情</a>
            <a-dropdown>
              <a class="ant-dropdown-link" @click.prevent>更多 <DownOutlined /></a>
              <template #overlay>
                <a-menu @click="({ key }) => handleAction(key, record)">
                  <a-menu-item key="PUBLISHED" v-if="record.status === 'DRAFT'">发布</a-menu-item>
                  <a-menu-item key="CLOSED" v-if="record.status === 'PUBLISHED'">下线</a-menu-item>
                  <a-menu-item key="DRAFT" v-if="record.status === 'CLOSED'">重新草稿</a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
            <a @click="handlePreview(record)">预览</a>
          </a-space>
        </template>
      </template>
    </a-table>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { message } from 'ant-design-vue'
import { PlusOutlined, DownOutlined } from '@ant-design/icons-vue'
import { getActivityPage, updateActivityStatus } from '../api/activity'

const loading = ref(false)
const keyword = ref('')
const status = ref('')
const dataSource = ref([])

const pagination = reactive({
  current: 1,
  pageSize: 10,
  total: 0,
})

const columns = [
  { title: 'ID', dataIndex: 'id', key: 'id', width: 80 },
  { title: '活动标题', dataIndex: 'title', key: 'title', ellipsis: true },
  { title: '活动类型', dataIndex: 'activityType', key: 'type', width: 120,
    customRender: ({ text }) => text === 'PAGE_REGISTER' ? '页面注册' : '弹窗注册' },
  { title: '状态', key: 'status', width: 100 },
  { title: '开始时间', dataIndex: 'startTime', key: 'startTime', width: 180 },
  { title: '结束时间', dataIndex: 'endTime', key: 'endTime', width: 180 },
  { title: '创建人', dataIndex: 'createdBy', key: 'createdBy', width: 100 },
  { title: '操作', key: 'action', width: 160, fixed: 'right' },
]

onMounted(() => fetchData())

async function fetchData() {
  loading.value = true
  try {
    const res = await getActivityPage({
      keyword: keyword.value,
      status: status.value,
      page: pagination.current,
      size: pagination.pageSize,
    })
    dataSource.value = res.data.list
    pagination.total = res.data.total
  } finally {
    loading.value = false
  }
}

function onSearch() {
  pagination.current = 1
  fetchData()
}

function onTableChange(pag) {
  pagination.current = pag.current
  pagination.pageSize = pag.pageSize
  fetchData()
}

async function handleAction(action, record) {
  await updateActivityStatus(record.id, action)
  message.success('操作成功')
  fetchData()
}

function handlePreview(record) {
  window.open(`/campaign/chunrihuanxin/${record.id}`, '_blank')
}
</script>

<style scoped>
.toolbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}
</style>
