package com.example.demo.subscribe;

/*
public class SingleSubscribe extends WebHookCallBack implements Subscribe {

*/
/*    @Override
    public void run(WebhookMessageDelivery delivery) {
        TenantContextHolder.setTenantId(delivery.getTenantId());
        WebhookDelivery webhookDelivery = webhookDeliveryService.getById(delivery.getDeliveryId());
        if (Objects.isNull(webhookDelivery)) {
            return;
        }
        HttpResult httpResult = webhookUtil.callWebhook(delivery);
        // 保存调用结果
        webhookDelivery = processWebHook(webhookDelivery, httpResult);
        if (webhookDelivery != null) {
            updateWebHookResult(webhookDelivery);
        }
        TenantContextHolder.clearTenant();*//*

    }
}
*/
