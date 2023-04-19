package Test;


import org.hibernate.Session;

import Util.HibernateUtil;
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Session ses = HibernateUtil.getSessionFactory().openSession();
        ses.beginTransaction();

        }


}
