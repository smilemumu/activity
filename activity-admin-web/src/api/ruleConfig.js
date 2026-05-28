import request from '../utils/request'

export function getRuleConfig(activityId) {
  return request.get(`/activity/${activityId}/rule-config`)
}

export function saveRuleConfig(activityId, data) {
  return request.put(`/activity/${activityId}/rule-config`, data)
}
