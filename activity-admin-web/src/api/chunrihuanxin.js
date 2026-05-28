import request from '../utils/request'

/**
 * 春日焕新活动 - API 接口
 */
export function register(activityId, data) {
  return request.post(`/activity/chunrihuanxin/${activityId}/register`, data)
}

export function getUserStatus(activityId, phone) {
  return request.get(`/activity/chunrihuanxin/${activityId}/status`, { params: { phone } })
}

export function claimReward(activityId, userId, stageCode) {
  return request.post(`/activity/chunrihuanxin/${activityId}/claim`, null, {
    params: { userId, stageCode }
  })
}

export function getInviteCode(activityId, userId) {
  return request.get(`/activity/chunrihuanxin/${activityId}/invite-code`, { params: { userId } })
}

export function getPageConfig(activityId) {
  return request.get(`/activity/${activityId}/page-config`)
}
