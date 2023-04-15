package org.example;
//create table product( id serial PRIMARY KEY,nameprod text UNIQUE,price numeric(10,2));
//insert into product(nameprod,price) VALUES('Кефир',50.0),
//        ('Шоколад',48.5),
//        ('Хлеб',20.75),
//        ('Колбаса',120.87),
//        ('Огурцы',109.67),
//        ('Сосиски',35.0),
//        ('Молоко',76.9);
//        select * from product;
// create table buyer( id serial PRIMARY KEY,name text UNIQUE);
//insert into buyer(name) VALUES('Андрей'),
//        ('Александр'),
//        ('Анна'),
//        ('Алексей');
//        select * from buyer;
//create table prod_buy(id serial PRIMARY KEY, id_product integer,id_buyer integer,
// FOREIGN KEY (id_product) REFERENCES product(id) ON DELETE CASCADE,
// FOREIGN KEY (id_buyer) REFERENCES buyer(id) ON DELETE CASCADE);
//ALTER TABLE prod_buy ALTER COLUMN id TYPE bigint;
//alter table prod_buy add column price numeric(10,2);
//insert into prod_buy(id_product,id_buyer) VALUES(3,1),
//        (2,1),
////        (6,1),
////        (6,2),
////        (3,4),
////        (7,3),
////        (1,3),
////        (2,2),
////        (5,4);
////        select * from prod_buy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Buyer.class)
                .addAnnotatedClass(PurchesesKey.class)
                .addAnnotatedClass(Purcheses.class)
                .buildSessionFactory();
        Session session = null;
        try {
            Scanner s= new Scanner(System.in);
            while( s.hasNext()) {
                String str = s.nextLine();
                if (str.equals("exit")) {
                    break;
                }
                String []  cmd = str.split(" ");
                switch (cmd[0]) {
//                    /showProductsByPerson имя_покупателя
                    case ("/showProductsByPerson"):
                        session= factory.getCurrentSession();
                        session.beginTransaction();
                        Buyer buyer= (Buyer) session.createQuery(" from Buyer b where b.name= :name")
                                        .setParameter("name",cmd[1]).getSingleResult();
                       List<Purcheses> purcheses=  buyer.getPurcheses();
                        for(Purcheses p: purcheses)
                                System.out.println(p.getProduct().toString());
                        session.getTransaction().commit();
                        break;
//                        /findPersonsByProductTitle название_товара
                    case ("/findPersonsByProductTitle"):
                        session= factory.getCurrentSession();
                        session.beginTransaction();
                        Product product= (Product) session.createQuery(" from Product p where p.nameProd= :name")
                                .setParameter("name",cmd[1]).getSingleResult();
                        purcheses = product.getPurcheses();
                        for(Purcheses p: purcheses)
                            System.out.println(p.getBuyer().toString());
                        session.getTransaction().commit();
                        break;
//                        /removePerson имя_элемента
                    case ("/removePerson"):
                        session= factory.getCurrentSession();
                        session.beginTransaction();
                       buyer= (Buyer) session.createQuery(" from Buyer b where b.name= :name")
                                .setParameter("name",cmd[1]).getSingleResult();
                        session.remove(buyer);
                        session.getTransaction().commit();
                        System.out.println("Покупатель удален");
                        break;
//                        /removeProduct имя_элемента
                    case ("/removeProduct"):
                        session= factory.getCurrentSession();
                        session.beginTransaction();
                        product= (Product) session.createQuery(" from Product p where p.nameProd= :name")
                                .setParameter("name",cmd[1]).getSingleResult();
                        session.remove(product);
                        session.getTransaction().commit();
                        System.out.println("Товар удален");
                        break;
//                        /buy имя_покупателя название_товара
                    case ("/buy"):
                        session= factory.getCurrentSession();
                        session.beginTransaction();
                        buyer= (Buyer) session.createQuery(" from Buyer b where b.name= :name")
                                .setParameter("name",cmd[1]).getSingleResult();
                         product= (Product) session.createQuery(" from Product p where p.nameProd= :name")
                                .setParameter("name",cmd[2]).getSingleResult();
                         PurchesesKey purchesesKey = new PurchesesKey();
                         purchesesKey.setProductId(product.getId());
                         purchesesKey.setBuyerId(buyer.getId());
                          Purcheses purcheses1= new Purcheses();
                          purcheses1.setId(purchesesKey);
                          purcheses1.setPrice(product.getPrice());
                          session.save(purcheses1);
                        session.getTransaction().commit();
                        break;
                    default:
                        break;
                }
            }
        } finally {
            factory.close();
            session.close();
        }
    }
}