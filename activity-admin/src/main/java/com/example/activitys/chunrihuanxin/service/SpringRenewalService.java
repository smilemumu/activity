package com.example.activitys.chunrihuanxin.service;

import com.example.activityConfig.common.BusinessException;
import com.example.activityConfig.entity.Activity;
import com.example.activityConfig.mapper.ActivityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 春日焕新活动 - 核心业务逻辑
 *
 * 业务流程：
 * 1. 用户进入活动页面 → 校验活动是否在有效期内
 * 2. 用户填写注册信息（姓名、手机号、身份证）→ 校验是否重复参与
 * 3. 如有邀请码 → 校验邀请码有效性，记录邀请关系
 * 4. 提交后创建用户记录，状态为 PENDING
 * 5. 实名认证通过后 → 状态变更为 SUCCESS，发放注册奖励
 * 6. 首次借款成功 → 发放首借奖励
 * 7. 授信额度达标 → 发放额度奖励
 * 8. 累计借款达标 → 发放阶梯奖励
 *
 * 核心校验规则：
 * - 同一手机号/身份证只能参与一次
 * - 活动需在有效期内（START_TIME ~ END_TIME）
 * - 活动状态需为 PUBLISHED
 * - 总参与人数上限控制（通过 RULE_CONFIG.totalClaimLimit）
 * - 地区限制（通过 RULE_CONFIG.regionLimitEnabled）
 */
@Service
public class SpringRenewalService {

    private final ActivityMapper activityMapper;
    // 后续注入：SpringRenewalUserMapper, SpringRenewalRewardRecordMapper 等

    public SpringRenewalService(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }

    /**
     * 用户注册参与活动
     * 1. 校验活动有效性
     * 2. 校验用户唯一性（同手机号/身份证不可重复参与）
     * 3. 校验邀请码（如有）
     * 4. 创建参与记录
     */
    @Transactional
    public void register(Long activityId, Object request) {
        Activity activity = activityMapper.findById(activityId);
        if (activity == null) {
            throw new BusinessException("活动不存在");
        }
        if (!"PUBLISHED".equals(activity.getStatus())) {
            throw new BusinessException("活动未发布");
        }
        // TODO: 实现完整的注册逻辑
    }

    /**
     * 查询用户参与状态
     * 返回各阶段完成情况和奖励领取状态
     */
    public Object getUserStatus(Long activityId, String phone) {
        // TODO: 根据手机号查询用户参与记录，组装各阶段状态返回
        return null;
    }

    /**
     * 领取阶段奖励
     * 校验该阶段是否已完成且未领取
     */
    @Transactional
    public void claimReward(Long userId, String stageCode) {
        // TODO: 校验阶段完成状态，发放奖励，记录领取日志
    }

    /**
     * 邀请裂变逻辑
     * 生成邀请码，记录邀请关系，被邀请人完成注册后发放邀请奖励
     */
    public String generateInviteCode(Long userId) {
        // TODO: 生成唯一邀请码
        return null;
    }
}
