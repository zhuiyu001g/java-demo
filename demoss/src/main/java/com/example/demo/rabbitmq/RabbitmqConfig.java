package com.example.demo.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class RabbitmqConfig {
    // 转账并且到账存队列
    public static final String TRANSFER_ACCOUNTS_DIC_QUEUE = "transfer_accounts_dic_queues";
    // 转账队列，判断转账是否已经被创建
    public static final String TRANSFER_ACCOUNTS_CREATE_QUEUE = "transfer_accounts_create_queues";
    // 转账并且到账交换机
    private static final String TRANSFER_ACCOUNTS_EXCHANGE_NAME = "transfer_accounts_exchange_names";

    // 1.定义转账队列
    @Bean
    public Queue TransferAccountsDicQueue() {
        return new Queue(TRANSFER_ACCOUNTS_DIC_QUEUE);
    }

    //1.定义补转账队列
    @Bean
    public Queue TransferAccountsQueue() {
        return new Queue(TRANSFER_ACCOUNTS_CREATE_QUEUE);
    }

    // 2.定义交换机
    @Bean
    DirectExchange directTransferAccountsExchange() {
        return new DirectExchange(TRANSFER_ACCOUNTS_EXCHANGE_NAME);
    }

    // 3.转账队列与交换机绑定
    @Bean
    Binding bindingExchangeOrderDicQueue() {
        return BindingBuilder.bind(TransferAccountsDicQueue()).to(directTransferAccountsExchange()).with("TransferAccountsRoutingKeys");
    }

    // 3.补转账列与交换机绑定
    @Bean
    Binding bindingExchangeCreateOrder() {
        return BindingBuilder.bind(TransferAccountsQueue()).to(directTransferAccountsExchange()).with("TransferAccountsRoutingKeys");
    }


}
