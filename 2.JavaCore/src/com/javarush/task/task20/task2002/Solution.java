package com.javarush.task.task20.task2002;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("your_file_name", null);
            //String yourFile = "..//humansave.txt";

            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user1 = new User();
            user1.setFirstName("Ivan");
            user1.setLastName("Petrov");
            user1.setBirthDate(Date.from(LocalDateTime.of(2000,2,1,10,11,12,13).atZone(ZoneId.systemDefault()).toInstant()));
            user1.setCountry(User.Country.RUSSIA);
            user1.setMale(true);
            javaRush.users.add(user1);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            if (loadedObject.equals(javaRush)) System.out.println("equals!");

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            char delimiter = 0x0A;
            int isUsersPresent = this.users != null ? 1 : 0;
            int isUsersFill = 0;
            if (this.users.size() > 0) isUsersFill = 1;
            outputStream.write(isUsersPresent);
            outputStream.write(isUsersFill);

            if (isUsersPresent == 1)
                for (User u : this.users
                ) {
                    outputStream.write(delimiter);
                    outputStream.write(u.getFirstName().getBytes());
                    outputStream.write(delimiter);
                    outputStream.write(u.getLastName().getBytes());
                    outputStream.write(delimiter);
                    LocalDateTime LocalDateTime = u.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    outputStream.write(LocalDateTime.toString().getBytes());
                    outputStream.write(delimiter);
                    outputStream.write(u.getCountry().getDisplayName().getBytes());
                    outputStream.write(delimiter);
                    outputStream.write(u.isMale() ? 1 : 0);
                }
            outputStream.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int isUsersPresent = reader.read();
            int isUsersFill = reader.read();
            if (isUsersPresent == 1 & isUsersFill == 1) {
                reader.read(); // read delimiter
                List<User> users = new ArrayList<>();
                while (reader.ready()) {
                    User user = new User();
                    String firstName = reader.readLine();
                    String lastName = reader.readLine();
                    Date birthDate = Date.from(LocalDateTime.parse(reader.readLine()).atZone(ZoneId.systemDefault()).toInstant());
                    String countryName = reader.readLine();

                    User.Country country = User.Country.OTHER;
                    for (User.Country s: User.Country.values()
                    ) {
                        if (countryName.equals(s.getDisplayName())) country = s;
                    }

                    boolean isMale = reader.read() == 1 ? true : false; reader.readLine();

                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setBirthDate(birthDate);
                    user.setCountry(country);
                    user.setMale(isMale);
                    users.add(user);
                }
                this.users = users;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
