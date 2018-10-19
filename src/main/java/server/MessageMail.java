package server;

import sun.misc.Queue;

public class MessageMail {
    String name;
    Queue<String> messageQueqe;
    boolean gameRequest;
    String gameRequestReciver;

    public MessageMail(String name) {
        this.name = name;
        messageQueqe = new Queue<>();
        gameRequest=false;
        gameRequestReciver=null;
    }

    public void addMessage(String value){
        messageQueqe.enqueue(value);
        System.out.println(value);
    }

    public String getMessage(){
        if(!messageQueqe.isEmpty()) {
            try {
                return messageQueqe.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty(){
        return messageQueqe.isEmpty();
    }

    public void gameRequest(String name){
        gameRequest = true;
        gameRequestReciver = name;
    }

    public boolean isGame(){
        if(gameRequest) {
            gameRequest =false;
            return true;
        }
        return false;
    }
}
