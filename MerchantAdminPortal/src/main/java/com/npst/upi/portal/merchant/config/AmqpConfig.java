package com.npst.upi.portal.merchant.config;

/*
 * import org.springframework.amqp.core.Binding; import
 * org.springframework.amqp.core.BindingBuilder; import
 * org.springframework.amqp.core.Queue; import
 * org.springframework.amqp.core.TopicExchange; import
 * org.springframework.amqp.rabbit.connection.ConnectionFactory; import
 * org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
 * import
 * org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * import com.npst.upi.portal.merchant.listener.MerchantTxnReceiver; import
 * com.npst.upi.portal.merchant.utility.Constants;
 */
/**
 * @author Rahul Chaudhary
 *
 */
/*
 * @Configuration public class AmqpConfig {
 * 
 * 
 * 
 * @Bean Queue notificationQueue() { return new Queue(Constants.MERCHANT_QUEUE,
 * true, false, false, null); }
 * 
 * @Bean(name = Constants.MERCHANTNOTIFICATION) TopicExchange
 * exchangeNotification() { return new
 * TopicExchange(Constants.MERCHANTNOTIFICATION); }
 * 
 * @Bean Binding notification(Queue merchantque, TopicExchange
 * notificationmerchant) { return
 * BindingBuilder.bind(merchantque).to(notificationmerchant)
 * .with(Constants.MERCHANT_QUEUE); }
 * 
 * @Bean SimpleMessageListenerContainer notificationContainer(ConnectionFactory
 * connectionFactory, MessageListenerAdapter notificationadapter) {
 * SimpleMessageListenerContainer simpleMessageListenerContainer = new
 * SimpleMessageListenerContainer();
 * simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
 * simpleMessageListenerContainer.setQueueNames(Constants.MERCHANT_QUEUE);
 * simpleMessageListenerContainer.setMessageListener(notificationadapter);
 * return simpleMessageListenerContainer; }
 * 
 * @Bean MessageListenerAdapter listenerAdapter(MerchantTxnReceiver receiver) {
 * return new MessageListenerAdapter(receiver, "receiveMessage"); }
 * 
 * }
 */