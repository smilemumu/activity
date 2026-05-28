import request from '../utils/request'

export function getRewards(activityId) {
  return request.get(`/activity/${activityId}/rewards`)
}

export function addReward(activityId, data) {
  return request.post(`/activity/${activityId}/rewards`, data)
}

export function updateReward(activityId, rewardId, data) {
  return request.put(`/activity/${activityId}/rewards/${rewardId}`, data)
}

export function deleteReward(activityId, rewardId) {
  return request.delete(`/activity/${activityId}/rewards/${rewardId}`)
}
