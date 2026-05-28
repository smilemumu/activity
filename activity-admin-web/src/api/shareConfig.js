import request from '../utils/request'

export function getShareConfig(activityId) {
  return request.get(`/activity/${activityId}/share-config`)
}

export function saveShareConfig(activityId, data) {
  return request.put(`/activity/${activityId}/share-config`, data)
}
