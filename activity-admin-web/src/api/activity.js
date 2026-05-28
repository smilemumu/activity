import request from '../utils/request'

export function createActivity(data) {
  return request.post('/activity', data)
}

export function updateActivity(id, data) {
  return request.put(`/activity/${id}`, data)
}

export function getActivityDetail(id) {
  return request.get(`/activity/${id}`)
}

export function getActivityPage(params) {
  return request.get('/activity/page', { params })
}

export function updateActivityStatus(id, status) {
  return request.put(`/activity/${id}/status`, null, { params: { status } })
}
