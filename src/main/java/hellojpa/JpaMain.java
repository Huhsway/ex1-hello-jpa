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

//            List<Member> result = em.createQuery("select m from Member as m", Member.class) // Member 객체를 대상으로 쿼리를 친다고 생각
//                    .setFirstResult(1)
//                    .setMaxResults(8) // 페이지 네이션할때 필요
//                    .getResultList();
//
//            for (Member member : result){
//                System.out.println("member.name = " + member.getName());
//            }

            // 비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            System.out.println("=== BEFORE ===");
            // 영속 (아직 DB에 저장은 안됨)
            em.persist(member);
//            // 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
//            em.detach(member);
//            // 객체를 삭제한 상태(삭제)
//            em.remove(member);
            System.out.println("=== AFTER ===");

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close(); // 꼭 닫아줘야함
        }
        emf.close();

    }

}
