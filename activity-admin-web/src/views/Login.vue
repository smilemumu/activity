<template>
  <div class="login-wrapper">
    <div class="login-card">
      <h1>活动配置后台</h1>
      <a-form :model="form" :rules="rules" @finish="handleLogin" layout="vertical" size="large">
        <a-form-item name="username">
          <a-input v-model:value="form.username" placeholder="用户名" autocomplete="off" />
        </a-form-item>
        <a-form-item name="password">
          <a-input-password v-model:value="form.password" placeholder="密码" />
        </a-form-item>
        <a-form-item>
          <a-button type="primary" html-type="submit" :loading="loading" block>
            登录
          </a-button>
        </a-form-item>
      </a-form>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { login } from '../api/auth'
import { message } from 'ant-design-vue'

const router = useRouter()
const loading = ref(false)
const form = reactive({ username: 'admin', password: '' })

const rules = {
  username: [{ required: true, message: '请输入用户名' }],
  password: [{ required: true, message: '请输入密码' }],
}

async function handleLogin() {
  loading.value = true
  try {
    const res = await login(form)
    localStorage.setItem('token', res.data.token)
    localStorage.setItem('username', res.data.username)
    localStorage.setItem('realName', res.data.realName)
    message.success('登录成功')
    router.push('/')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-wrapper {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.login-card {
  width: 400px;
  padding: 40px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.15);
}

.login-card h1 {
  text-align: center;
  margin-bottom: 32px;
  color: #333;
  font-size: 24px;
}
</style>
