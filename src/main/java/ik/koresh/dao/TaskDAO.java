//package ik.koresh.dao;
//
//
//
//import ik.koresh.models.Task;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Component
//public class TaskDAO {
//
//    private final SessionFactory sessionFactory;
//
//
//    @Autowired
//    public TaskDAO(SessionFactory sessionFactory){
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Task> index(){
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.createQuery("select t from Task t", Task.class).getResultList();
//    }
//
//    public Task show(int id){
//        Session session = sessionFactory.getCurrentSession(); // для работы с Hibernate
//        return session.get(Task.class, id);
//    }
//
//    public void save(Task task){
//        Session session = sessionFactory.getCurrentSession(); // для работы с Hibernate
//        session.save(task);
//    }
//
//    public void update(int id, Task updateTask) {
//        Session session = sessionFactory.getCurrentSession();
//        Task taskToBeUpdated = session.get(Task.class, id);
//        taskToBeUpdated.setDescription(updateTask.getDescription());
//        taskToBeUpdated.setStatus(updateTask.getStatus());
//        taskToBeUpdated.setDeadline(updateTask.getDeadline());
//
//    }
//
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        session.remove(session.get(Task.class, id));
//
//    }
//}
