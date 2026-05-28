package com.example.activityConfig.service;

import com.example.activityConfig.common.BusinessException;
import com.example.activityConfig.dto.*;
import com.example.activityConfig.entity.Activity;
import com.example.activityConfig.entity.ActivityDeptRel;
import com.example.activityConfig.entity.ActivityOrgRel;
import com.example.activityConfig.mapper.ActivityDeptRelMapper;
import com.example.activityConfig.mapper.ActivityMapper;
import com.example.activityConfig.mapper.ActivityOrgRelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityMapper activityMapper;
    private final ActivityOrgRelMapper orgRelMapper;
    private final ActivityDeptRelMapper deptRelMapper;

    public ActivityService(ActivityMapper activityMapper,
                           ActivityOrgRelMapper orgRelMapper,
                           ActivityDeptRelMapper deptRelMapper) {
        this.activityMapper = activityMapper;
        this.orgRelMapper = orgRelMapper;
        this.deptRelMapper = deptRelMapper;
    }

    @Transactional
    public Long create(ActivityRequest request, String createdBy) {
        Activity activity = toEntity(request);
        activity.setStatus("DRAFT");
        activity.setCreatedBy(createdBy);
        activityMapper.insert(activity);

        saveOrgRels(activity.getId(), request.getOrgRels());
        saveDeptRels(activity.getId(), request.getDeptRels());

        return activity.getId();
    }

    @Transactional
    public void update(Long id, ActivityRequest request) {
        Activity existing = activityMapper.findById(id);
        if (existing == null) {
            throw new BusinessException("活动不存在");
        }

        Activity activity = toEntity(request);
        activity.setId(id);
        // 保留已有状态，避免被覆盖为 NULL
        if (activity.getStatus() == null) {
            activity.setStatus(existing.getStatus());
        }
        activityMapper.update(activity);

        orgRelMapper.deleteByActivityId(id);
        saveOrgRels(id, request.getOrgRels());

        deptRelMapper.deleteByActivityId(id);
        saveDeptRels(id, request.getDeptRels());
    }

    public ActivityResponse getDetail(Long id) {
        Activity activity = activityMapper.findById(id);
        if (activity == null) {
            throw new BusinessException("活动不存在");
        }
        return toResponse(activity);
    }

    public PageResult<ActivityResponse> page(ActivityPageRequest request) {
        List<Activity> list = activityMapper.findByPage(
                request.getKeyword(), request.getStatus(),
                request.getOffset(), request.getSize());
        int total = activityMapper.countByPage(request.getKeyword(), request.getStatus());

        List<ActivityResponse> rows = list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return new PageResult<>(rows, total, request.getPage(), request.getSize());
    }

    public void updateStatus(Long id, String status) {
        Activity activity = activityMapper.findById(id);
        if (activity == null) {
            throw new BusinessException("活动不存在");
        }
        activity.setStatus(status);
        activityMapper.update(activity);
    }

    private void saveOrgRels(Long activityId, List<ActivityRequest.OrgRelItem> items) {
        if (items != null) {
            for (ActivityRequest.OrgRelItem item : items) {
                ActivityOrgRel rel = new ActivityOrgRel();
                rel.setActivityId(activityId);
                rel.setOrgCode(item.getOrgCode());
                rel.setOrgName(item.getOrgName());
                orgRelMapper.insert(rel);
            }
        }
    }

    private void saveDeptRels(Long activityId, List<ActivityRequest.DeptRelItem> items) {
        if (items != null) {
            for (ActivityRequest.DeptRelItem item : items) {
                ActivityDeptRel rel = new ActivityDeptRel();
                rel.setActivityId(activityId);
                rel.setDeptName(item.getDeptName());
                rel.setContactName(item.getContactName());
                deptRelMapper.insert(rel);
            }
        }
    }

    private Activity toEntity(ActivityRequest request) {
        Activity a = new Activity();
        a.setActivityType(request.getActivityType());
        a.setTitle(request.getTitle());
        a.setDescription(request.getDescription());
        a.setStartTime(request.getStartTime());
        a.setEndTime(request.getEndTime());
        a.setCoverImage(request.getCoverImage());
        return a;
    }

    private ActivityResponse toResponse(Activity activity) {
        ActivityResponse r = new ActivityResponse();
        r.setId(activity.getId());
        r.setActivityType(activity.getActivityType());
        r.setTitle(activity.getTitle());
        r.setDescription(activity.getDescription());
        r.setStartTime(activity.getStartTime());
        r.setEndTime(activity.getEndTime());
        r.setCoverImage(activity.getCoverImage());
        r.setStatus(activity.getStatus());
        r.setCreatedBy(activity.getCreatedBy());
        r.setCreatedTime(activity.getCreatedTime());
        r.setUpdatedTime(activity.getUpdatedTime());

        List<ActivityOrgRel> orgRels = orgRelMapper.findByActivityId(activity.getId());
        r.setOrgRels(orgRels.stream().map(rel -> {
            ActivityRequest.OrgRelItem item = new ActivityRequest.OrgRelItem();
            item.setOrgCode(rel.getOrgCode());
            item.setOrgName(rel.getOrgName());
            return item;
        }).collect(Collectors.toList()));

        List<ActivityDeptRel> deptRels = deptRelMapper.findByActivityId(activity.getId());
        r.setDeptRels(deptRels.stream().map(rel -> {
            ActivityRequest.DeptRelItem item = new ActivityRequest.DeptRelItem();
            item.setDeptName(rel.getDeptName());
            item.setContactName(rel.getContactName());
            return item;
        }).collect(Collectors.toList()));

        return r;
    }
}
