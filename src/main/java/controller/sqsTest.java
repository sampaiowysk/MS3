package controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

import java.util.List;
import java.util.Map.Entry;

public class sqsTest {
    public static void main (String[] args){

        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        final String myQueueUrl = "https://sqs.us-east-1.amazonaws.com/183159870389/TestSQS";

        // List all queues.
        for (final String queueUrl : sqs.listQueues().getQueueUrls()) {
            System.out.println("  QueueUrl: " + queueUrl);
        }

        // Send a message.
        System.out.println("Sending a message to MyQueue.\n");
        sqs.sendMessage(new SendMessageRequest(myQueueUrl, "This is my message text 1."));
        sqs.sendMessage(new SendMessageRequest(myQueueUrl, "This is my message text 2."));
        sqs.sendMessage(new SendMessageRequest(myQueueUrl, "This is my message text 3."));

        // Receive messages.
        /*System.out.println("Receiving messages from MyQueue.\n");
        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
        final List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        for (final Message message : messages) {
            System.out.println("Message");
            System.out.println("  MessageId:     " + message.getMessageId());
            System.out.println("  ReceiptHandle: " + message.getReceiptHandle());
            System.out.println("  MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("  Body:          " + message.getBody());
            for (final Entry<String, String> entry : message.getAttributes().entrySet()) {
                System.out.println("Attribute");
                System.out.println("  Name:  " + entry.getKey());
                System.out.println("  Value: " + entry.getValue());
            }
        }

        // Delete the message.
        System.out.println("Deleting a message.\n");
        final String messageReceiptHandle = messages.get(0).getReceiptHandle();
        sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl, messageReceiptHandle));*/

    }
}
