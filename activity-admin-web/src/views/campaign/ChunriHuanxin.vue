<template>
  <!--
    春日焕新活动 - 移动端 H5 营销落地页
    诚e贷 春季营销活动 - 南银法巴消费金融
    设计尺寸：375px 基准（移动端）
  -->
  <div class="spring-campaign">
    <!-- 顶部导航 -->
    <div class="campaign-header">
      <div class="header-logo">诚e贷</div>
      <div class="header-btn" @click="showRules = true">活动规则</div>
    </div>

    <!-- ===== 01. 主视觉区 Hero Banner ===== -->
    <section class="hero-section">
      <div class="hero-bg">
        <div class="hero-decoration">
          <span class="petal p1"></span>
          <span class="petal p2"></span>
          <span class="petal p3"></span>
          <span class="petal p4"></span>
          <span class="petal p5"></span>
          <span class="petal p6"></span>
        </div>
      </div>
      <div class="hero-content">
        <h1 class="hero-title">春日焕新</h1>
        <p class="hero-subtitle">诚e贷新用户专享礼遇</p>
        <div class="hero-amount-badge">
          <div class="amount-number">300,000</div>
          <div class="amount-label">最高可贷额度（元）</div>
        </div>
        <p class="hero-desc">具体额度以实际审批为准</p>
      </div>
    </section>

    <!-- ===== 02. 四大卖点卡片区 ===== -->
    <section class="features-section">
      <div class="features-grid">
        <div class="feature-card" v-for="(feat, i) in features" :key="i">
          <div class="feature-icon-wrap">
            <span class="feature-icon">{{ feat.icon }}</span>
          </div>
          <div class="feature-title">{{ feat.title }}</div>
          <div class="feature-value">{{ feat.value }}</div>
        </div>
      </div>
    </section>

    <!-- ===== 03. 优惠利率文案 ===== -->
    <section class="promo-section">
      <div class="promo-card">
        <div class="promo-icon">🎓</div>
        <p class="promo-text">
          大专及以上学历，且有稳定工作和收入的优质客户，可享受<span class="promo-highlight">年化利率（单利）低至3.09%</span>优惠福利
        </p>
      </div>
    </section>

    <!-- ===== 04. CTA 立即申请按钮 ===== -->
    <section class="cta-section">
      <button class="cta-btn" @click="scrollToApply">立即申请</button>
    </section>

    <!-- ===== 05. 注册表单区 ===== -->
    <section class="form-section" id="apply-form">
      <div class="section-header">
        <h2 class="section-title">在线申请</h2>
        <p class="section-subtitle">填写信息，立即获取额度</p>
      </div>
      <div class="form-card">
        <div class="form-item">
          <label class="form-label">真实姓名</label>
          <input
            v-model="form.realName"
            class="form-input"
            placeholder="请输入真实姓名"
            :disabled="submitted"
          />
        </div>
        <div class="form-item">
          <label class="form-label">手机号码</label>
          <input
            v-model="form.phone"
            class="form-input"
            placeholder="请输入手机号码"
            maxlength="11"
            :disabled="submitted"
          />
        </div>
        <div class="form-item">
          <label class="form-label">身份证号</label>
          <input
            v-model="form.idCard"
            class="form-input"
            placeholder="请输入身份证号码"
            :disabled="submitted"
          />
        </div>
        <div class="form-agreement">
          <label class="agreement-check">
            <input type="checkbox" v-model="agreed" :disabled="submitted" />
            <span>我已阅读并同意 <a href="javascript:;" @click="showAgreement">《用户服务协议》</a>和<a href="javascript:;" @click="showAgreement">《隐私政策》</a></span>
          </label>
        </div>
        <button
          class="form-submit-btn"
          :class="{ disabled: submitted || !canSubmit }"
          :disabled="submitted || !canSubmit"
          @click="handleRegister"
        >
          <span v-if="loading" class="btn-loading"></span>
          <span v-else-if="submitted">已提交</span>
          <span v-else>立即申请</span>
        </button>
      </div>
    </section>

    <!-- ===== 06. 合作机构 ===== -->
    <section class="partner-section">
      <div class="section-header">
        <h2 class="section-title">合作机构</h2>
      </div>
      <div class="partner-list">
        <div class="partner-item" v-for="(p, i) in partners" :key="i">
          <div class="partner-name">{{ p }}</div>
        </div>
      </div>
    </section>

    <!-- ===== 07. 底部信息 ===== -->
    <footer class="campaign-footer">
      <div class="footer-risk">
        <p class="risk-title">风险提示</p>
        <p>贷款有风险，借款需谨慎</p>
        <p>请根据个人能力合理贷款</p>
        <p>年化利率（单利）3.0%-19.8%，以等额本息约定期限还款为例</p>
        <p>具体额度、放款时间、利率以实际审批结果为准，无额外费用</p>
      </div>
      <div class="footer-copyright">
        <p>本活动最终解释权归南银法巴消费金融有限公司所有</p>
        <p>客服热线：400-XXX-XXXX</p>
        <p>南银法巴消费金融有限公司 版权所有</p>
        <p>苏ICP备15024947号-1</p>
      </div>
    </footer>

    <!-- ===== 弹窗：活动规则 ===== -->
    <div class="modal-overlay" v-if="showRules" @click="showRules = false">
      <div class="modal-content rules-modal" @click.stop>
        <div class="modal-header">
          <h3>活动规则</h3>
          <button class="modal-close" @click="showRules = false">✕</button>
        </div>
        <div class="modal-body">
          <ul class="rules-list">
            <li v-for="(rule, i) in activityRules" :key="i">{{ i + 1 }}. {{ rule }}</li>
          </ul>
        </div>
        <div class="modal-footer">
          <button class="modal-confirm-btn" @click="showRules = false">我知道了</button>
        </div>
      </div>
    </div>

    <!-- ===== 弹窗：申请成功 ===== -->
    <div class="modal-overlay" v-if="showSuccessModal">
      <div class="modal-content success-modal">
        <div class="success-icon">🎉</div>
        <h3>申请已提交</h3>
        <p>恭喜你成功参与春日焕新活动</p>
        <p class="success-hint">具体额度以实际审批为准，请保持手机畅通</p>
        <button class="modal-confirm-btn" @click="showSuccessModal = false">好的</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'
import { register } from '../../api/chunrihuanxin'
import { message } from 'ant-design-vue'

const props = defineProps({
  activityId: { type: Number, required: true }
})

// ===== 状态 =====
const loading = ref(false)
const submitted = ref(false)
const agreed = ref(false)
const showRules = ref(false)
const showSuccessModal = ref(false)

const form = reactive({
  realName: '',
  phone: '',
  idCard: ''
})

// ===== 四大卖点 =====
const features = [
  { icon: '💰', title: '灵活借款', value: '最高可贷30W' },
  { icon: '📊', title: '利率透明', value: '年化利率（单利）\n3.0%-19.8%' },
  { icon: '⚡', title: '流程高效', value: '线上审批\n到账快至3分钟' },
  { icon: '🏦', title: '无手续费', value: '银行背景\n不收取额外费用' }
]

// ===== 合作机构 =====
const partners = [
  '南京银行',
  '法国巴黎银行',
  '国际金融公司（IFC）',
  '法国巴黎银行个人金融集团'
]

// ===== 活动规则 =====
const activityRules = [
  '活动仅限诚e贷新注册用户参与，每位用户限参与一次。',
  '具体额度以实际审批为准，大专及以上学历且有稳定工作和收入的优质客户可享受年化利率低至3.09%优惠福利。',
  '年化利率（单利）范围为3.0%-19.8%，以等额本息约定期限还款为例。',
  '放款时间以实际审批进度为准，最快可在3分钟内到账。',
  '本产品由南银法巴消费金融有限公司提供，无任何额外手续费。',
  '如发现作弊、刷单等违规行为，主办方有权取消参与资格并追回已发放奖励。',
  '本活动最终解释权归南银法巴消费金融有限公司所有。'
]

// ===== 计算属性 =====
const canSubmit = computed(() => {
  return form.realName && form.phone && form.idCard && agreed.value
})

// ===== 方法 =====
function scrollToApply() {
  const el = document.getElementById('apply-form')
  if (el) el.scrollIntoView({ behavior: 'smooth' })
}

async function handleRegister() {
  if (!canSubmit.value) return
  loading.value = true
  try {
    await register(props.activityId, { ...form })
    submitted.value = true
    showSuccessModal.value = true
  } catch (err) {
    message.error(err.message || '提交失败，请重试')
  } finally {
    loading.value = false
  }
}

function showAgreement() {
  message.info('协议页面开发中')
}
</script>

<style scoped>
/* ===== 全局重置 ===== */
.spring-campaign {
  width: 100%;
  max-width: 480px;
  margin: 0 auto;
  min-height: 100vh;
  font-family: -apple-system, BlinkMacSystemFont, 'PingFang SC', 'Helvetica Neue', sans-serif;
  background: linear-gradient(180deg, #FFF8F0 0%, #FFF5EE 10%, #FFFFFF 25%, #FFFBF5 100%);
  color: #333;
  overflow-x: hidden;
  -webkit-font-smoothing: antialiased;
}

/* ===== 顶部导航 ===== */
.campaign-header {
  position: sticky;
  top: 0;
  z-index: 100;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 20px;
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
}
.header-logo {
  font-size: 18px;
  font-weight: 700;
  color: #E8618C;
  letter-spacing: 1px;
}
.header-btn {
  font-size: 13px;
  color: #999;
  padding: 6px 14px;
  border: 1px solid #E0E0E0;
  border-radius: 20px;
  cursor: pointer;
}

/* ===== Hero Banner ===== */
.hero-section {
  position: relative;
  overflow: hidden;
  background: linear-gradient(180deg, #FFECD2 0%, #FFE4D0 25%, #FFD4C0 60%, #FFE8D6 100%);
  text-align: center;
  padding: 48px 24px 56px;
}
.hero-decoration {
  position: absolute;
  inset: 0;
  pointer-events: none;
}
.petal {
  position: absolute;
  width: 24px;
  height: 24px;
  border-radius: 50% 0 50% 0;
  opacity: 0.35;
}
.petal:nth-child(1) { top: 8%; left: 5%; background: #FFB7C5; transform: rotate(30deg); }
.petal:nth-child(2) { top: 15%; right: 10%; background: #FFD1A9; transform: rotate(75deg); }
.petal:nth-child(3) { top: 45%; left: 8%; background: #FFC4D9; transform: rotate(120deg); }
.petal:nth-child(4) { bottom: 25%; right: 6%; background: #FFB5C2; transform: rotate(200deg); }
.petal:nth-child(5) { bottom: 35%; left: 18%; background: #FFDAB9; transform: rotate(260deg); }
.petal:nth-child(6) { top: 60%; right: 20%; background: #FFE4C4; transform: rotate(330deg); }

.hero-content { position: relative; z-index: 1; }
.hero-title {
  font-size: 38px;
  font-weight: 800;
  background: linear-gradient(135deg, #E8618C, #FF7E5F);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  letter-spacing: 6px;
  margin-bottom: 10px;
}
.hero-subtitle {
  font-size: 16px;
  color: #B8735A;
  font-weight: 400;
  letter-spacing: 2px;
  margin-bottom: 28px;
}

.hero-amount-badge {
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  background: #FFF;
  border-radius: 20px;
  padding: 20px 40px;
  box-shadow: 0 6px 30px rgba(232, 97, 140, 0.15);
  margin-bottom: 16px;
}
.amount-number {
  font-size: 52px;
  font-weight: 900;
  color: #E8618C;
  line-height: 1.1;
  letter-spacing: 2px;
}
.amount-label {
  font-size: 14px;
  color: #999;
  margin-top: 6px;
}

.hero-desc {
  font-size: 13px;
  color: #A0766C;
}

/* ===== 通用区块样式 ===== */
.section-header { text-align: center; margin-bottom: 20px; }
.section-title {
  font-size: 20px;
  font-weight: 700;
  color: #333;
  margin-bottom: 6px;
}
.section-subtitle { font-size: 13px; color: #999; }

/* ===== 四大卖点卡片 ===== */
.features-section {
  padding: 36px 20px;
  margin-top: -20px;
  position: relative;
  z-index: 2;
}
.features-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 12px;
}
.feature-card {
  background: #FFF;
  border-radius: 14px;
  padding: 20px 16px;
  text-align: center;
  box-shadow: 0 2px 16px rgba(0, 0, 0, 0.05);
  border: 1px solid #F5F0EC;
  transition: transform 0.2s, box-shadow 0.2s;
}
.feature-card:active {
  transform: scale(0.97);
  box-shadow: 0 4px 20px rgba(232, 97, 140, 0.12);
}
.feature-icon-wrap {
  width: 48px;
  height: 48px;
  margin: 0 auto 12px;
  background: linear-gradient(135deg, #FFF5F0, #FFE8E0);
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.feature-icon { font-size: 26px; }
.feature-title {
  font-size: 16px;
  font-weight: 700;
  color: #333;
  margin-bottom: 8px;
}
.feature-value {
  font-size: 13px;
  color: #E8618C;
  font-weight: 500;
  line-height: 1.6;
  white-space: pre-line;
}

/* ===== 优惠利率文案 ===== */
.promo-section {
  padding: 0 20px 36px;
}
.promo-card {
  background: linear-gradient(135deg, #FFFAF5, #FFF0E8);
  border-radius: 14px;
  padding: 20px 24px;
  display: flex;
  align-items: flex-start;
  gap: 12px;
  border: 1px solid #FFE4D0;
}
.promo-icon {
  font-size: 28px;
  flex-shrink: 0;
  margin-top: 2px;
}
.promo-text {
  font-size: 14px;
  color: #666;
  line-height: 1.8;
  margin: 0;
}
.promo-highlight {
  color: #E8618C;
  font-weight: 600;
}

/* ===== CTA 按钮区 ===== */
.cta-section {
  padding: 0 20px 40px;
}
.cta-btn {
  width: 100%;
  height: 50px;
  border: none;
  border-radius: 25px;
  background: linear-gradient(135deg, #FF7E5F, #E8618C);
  color: #FFF;
  font-size: 18px;
  font-weight: 700;
  cursor: pointer;
  box-shadow: 0 4px 16px rgba(232, 97, 140, 0.3);
  transition: transform 0.1s, box-shadow 0.2s;
}
.cta-btn:active {
  transform: scale(0.98);
  box-shadow: 0 2px 8px rgba(232, 97, 140, 0.2);
}

/* ===== Form 注册表单 ===== */
.form-section { padding: 40px 20px; }
.form-card {
  background: #FFF;
  border-radius: 16px;
  padding: 24px 20px;
  box-shadow: 0 2px 16px rgba(0, 0, 0, 0.06);
}
.form-item { margin-bottom: 16px; }
.form-label {
  display: block;
  font-size: 14px;
  font-weight: 500;
  color: #333;
  margin-bottom: 6px;
}
.form-input {
  width: 100%;
  height: 44px;
  padding: 0 14px;
  border: 1px solid #E8E8E8;
  border-radius: 10px;
  font-size: 15px;
  color: #333;
  background: #FAFAFA;
  transition: border-color 0.2s;
  box-sizing: border-box;
  outline: none;
}
.form-input:focus { border-color: #E8618C; background: #FFF; }
.form-input:disabled { background: #F5F5F5; color: #999; }

.form-agreement { margin: 20px 0; font-size: 12px; color: #999; }
.agreement-check { display: flex; align-items: flex-start; gap: 6px; cursor: pointer; }
.agreement-check input[type="checkbox"] { margin-top: 2px; accent-color: #E8618C; }
.agreement-check a { color: #E8618C; text-decoration: none; }

.form-submit-btn {
  width: 100%;
  height: 48px;
  border: none;
  border-radius: 24px;
  background: linear-gradient(135deg, #FF7E5F, #E8618C);
  color: #FFF;
  font-size: 17px;
  font-weight: 600;
  cursor: pointer;
  transition: opacity 0.2s, transform 0.1s;
}
.form-submit-btn:active:not(.disabled) { transform: scale(0.98); }
.form-submit-btn.disabled { opacity: 0.5; cursor: not-allowed; }
.btn-loading {
  display: inline-block;
  width: 18px; height: 18px;
  border: 2px solid rgba(255,255,255,0.3);
  border-top-color: #FFF;
  border-radius: 50%;
  animation: spin 0.6s linear infinite;
}
@keyframes spin { to { transform: rotate(360deg); } }

/* ===== 合作机构 ===== */
.partner-section {
  padding: 40px 20px;
}
.partner-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 12px;
}
.partner-item {
  background: #FFF;
  border: 1px solid #F0EDE8;
  border-radius: 10px;
  padding: 14px 20px;
  text-align: center;
  box-shadow: 0 1px 8px rgba(0,0,0,0.03);
}
.partner-name {
  font-size: 14px;
  font-weight: 600;
  color: #555;
}

/* ===== Footer ===== */
.campaign-footer {
  padding: 32px 24px 40px;
  text-align: center;
  font-size: 12px;
  color: #BBB;
  line-height: 2;
}
.footer-risk {
  background: #FAFAFA;
  border-radius: 12px;
  padding: 16px;
  margin-bottom: 20px;
  line-height: 2;
}
.risk-title {
  font-size: 14px;
  font-weight: 600;
  color: #999;
  margin-bottom: 4px;
}
.footer-copyright {
  line-height: 2;
}

/* ===== Modal ===== */
.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0,0,0,0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 200;
  padding: 24px;
}
.modal-content {
  background: #FFF;
  border-radius: 16px;
  width: 100%;
  max-width: 360px;
  max-height: 80vh;
  overflow-y: auto;
  padding: 24px;
}
.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}
.modal-header h3 { font-size: 18px; font-weight: 700; }
.modal-close {
  width: 28px; height: 28px;
  border: none; background: #F5F5F5;
  border-radius: 50%; font-size: 14px; color: #999;
  cursor: pointer;
}
.modal-body { margin-bottom: 20px; }
.rules-list { padding-left: 0; list-style: none; }
.rules-list li {
  font-size: 13px;
  color: #666;
  line-height: 1.8;
  padding: 4px 0;
}
.modal-confirm-btn {
  width: 100%;
  height: 44px;
  border: none;
  border-radius: 22px;
  background: linear-gradient(135deg, #FF7E5F, #E8618C);
  color: #FFF;
  font-size: 15px;
  font-weight: 600;
  cursor: pointer;
}
.modal-footer { text-align: center; }

.success-modal { text-align: center; }
.success-icon { font-size: 56px; margin-bottom: 12px; }
.success-modal h3 { font-size: 20px; margin-bottom: 8px; }
.success-modal p { font-size: 14px; color: #666; margin-bottom: 6px; }
.success-hint { font-size: 12px; color: #E8618C; }
</style>
