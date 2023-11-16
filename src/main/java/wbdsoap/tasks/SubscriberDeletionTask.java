package wbdsoap.tasks;

import wbdsoap.daos.SubscriptionDAO;
import wbdsoap.utils.HibernateUtil;
import wbdsoap.utils.ServerUtil;

import java.util.Timer;
import java.util.TimerTask;

public class SubscriberDeletionTask extends TimerTask {
    private  final SubscriptionDAO subscriptionDAO;

    public SubscriberDeletionTask(){
        this.subscriptionDAO = new SubscriptionDAO(HibernateUtil.getSessionFactory());
    }

    @Override
    public void run() {
         this.subscriptionDAO.deleteExpiredSubs();
    }

    public static void main(String[] args) {
        Timer timer = new Timer(true);
        long delay = 0;
        long period = ServerUtil.subDeletionRoutineTime;

        timer.scheduleAtFixedRate(new SubscriberDeletionTask(), delay, period);
    }
}