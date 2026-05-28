import request from '../utils/request'

export function getPageConfig(activityId) {
  return request.get(`/activity/${activityId}/page-config`)
}

export function savePageConfig(activityId, data) {
  return request.put(`/activity/${activityId}/page-config`, data)
}
