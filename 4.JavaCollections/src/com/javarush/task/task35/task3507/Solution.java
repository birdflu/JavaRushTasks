package com.javarush.task.task35.task3507;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(
                Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();
        ClassLoader classLoader = Solution.class.getClassLoader();
    
        File folder = new File(pathToAnimals);
    
        String[] files = folder.list();
    
        for ( String fileName : files ) {
            boolean isPublicConstructor = false;
            boolean isImplAnimal = false;
            try {
                String className = Solution.class.getPackage().getName() + "." +  "data" + "." + fileName.replace(".class", "");
//                System.out.println("File: " + pathToAnimals + "/" + fileName);
//                System.out.println("Class: " + className);
                Class<? extends Animal> clazz  = (Class<? extends Animal>) classLoader.loadClass(className);
                Class<?>[] parameterTypes = {};
                Constructor constructor = clazz.getDeclaredConstructor(parameterTypes);
                
//                System.out.println("constructor = " + constructor);
                if (constructor.getModifiers() == Modifier.PUBLIC)
                    isPublicConstructor = true;

                Class[] interfaces = clazz.getInterfaces();
                for (Class intface: Arrays.asList(interfaces)
                     ) {
                    if ("Animal".equals(intface.getSimpleName()))
                        isImplAnimal = true;
                }
                if (isImplAnimal && isPublicConstructor)
                {
                    Animal animal = clazz.newInstance();
                    set.add(animal);
                }
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
               // e.printStackTrace();
            }
    
        }
    
        return set;
    }
}
