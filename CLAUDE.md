# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 仓库概述

这是一个**产品设计文档仓库**，不是代码仓库。存放活动配置后台系统的产品需求与交互原型。

## 文件说明

### `活动配置-注册类-页面注册or弹窗注册.xmind`
XMind 思维导图（ZIP 格式），梳理了"注册类活动"配置后台的完整功能需求。解析方式：

```bash
# XMind 文件是 ZIP 包，核心内容在 content.json 中
node -e "
const fs = require('fs'); const zlib = require('zlib');
const data = fs.readFileSync('活动配置-注册类-页面注册or弹窗注册.xmind');
// 遍历 ZIP local file headers，找到 content.json 并用 zlib.inflateRawSync 解压
"
```

### `配置活动.rp`
Axure RP 原型文件（~2MB），是上述需求对应的交互原型设计稿。

## 业务背景

- 系统归属：**xxx公司**（消费金融）
- 归属机构：机构A(101)、机构B(301)
- 涉及产品：产品A(0302)、产品B(PLON)、产品C(0303)、产品D(0305)、产品E(0307)、产品F(SFCL)
- 活动类型：注册类（页面注册 / 弹窗注册）
