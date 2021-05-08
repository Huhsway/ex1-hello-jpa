package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // 객체를 대신 저장해주는 놈이라 생각하자

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member findMember = em.find(Member.class, 1L);
            List<Member> result = em.createQuery("select m from Member as m", Member.class) // Member 객체를 대상으로 쿼리를 친다고 생각
                    .setFirstResult(1)
                    .setMaxResults(8) // 페이지 네이션할때 필요
                    .getResultList();

            for (Member member : result){
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close(); // 꼭 닫아줘야함
        }
        emf.close();

    }

}
