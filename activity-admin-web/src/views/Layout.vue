<template>
  <a-layout style="min-height: 100vh">
    <a-layout-sider v-model:collapsed="collapsed" collapsible theme="dark">
      <div class="logo">活动配置后台</div>
      <a-menu theme="dark" mode="inline" v-model:selectedKeys="selectedKeys" @click="onMenuClick">
        <a-menu-item key="/activities">
          <unordered-list-outlined />
          <span>活动管理</span>
        </a-menu-item>
        <a-menu-item key="/dashboard">
          <dashboard-outlined />
          <span>数据看板</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>

    <a-layout>
      <a-layout-header class="header">
        <div class="header-right">
          <a-dropdown>
            <a class="user-info" @click.prevent>
              <user-outlined />
              {{ realName || username }}
            </a>
            <template #overlay>
              <a-menu>
                <a-menu-item key="logout" @click="handleLogout">
                  <logout-outlined /> 退出登录
                </a-menu-item>
              </a-menu>
            </template>
          </a-dropdown>
        </div>
      </a-layout-header>

      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import {
  UnorderedListOutlined,
  DashboardOutlined,
  UserOutlined,
  LogoutOutlined,
} from '@ant-design/icons-vue'

const router = useRouter()
const route = useRoute()
const collapsed = ref(false)
const username = localStorage.getItem('username') || ''
const realName = localStorage.getItem('realName') || ''

const selectedKeys = ref(['/activities'])

watch(
  () => route.path,
  (path) => {
    if (path.startsWith('/dashboard')) selectedKeys.value = ['/dashboard']
    else selectedKeys.value = ['/activities']
  },
  { immediate: true }
)

function onMenuClick({ key }) {
  router.push(key)
}

function handleLogout() {
  localStorage.clear()
  router.push('/login')
}
</script>

<style scoped>
.logo {
  height: 64px;
  line-height: 64px;
  text-align: center;
  color: #fff;
  font-size: 18px;
  font-weight: bold;
  white-space: nowrap;
  overflow: hidden;
}

.header {
  background: #fff;
  padding: 0 24px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.08);
}

.header-right {
  display: flex;
  align-items: center;
}

.user-info {
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
}

.content {
  margin: 16px;
  padding: 24px;
  background: #fff;
  border-radius: 4px;
  min-height: 280px;
}
</style>
