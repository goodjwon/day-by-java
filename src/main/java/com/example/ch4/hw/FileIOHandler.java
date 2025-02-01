package com.example.ch4.hw;

import java.io.*;
import java.util.*;
import java.util.zip.*;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
// XML 예시: Jackson, JAXB
// YAML 예시: SnakeYAML
// Excel 예시: Apache POI

public class FileIOHandler {
    //1) 일반 텍스트 파일 쓰기
    public static void writeTextFile(String filename, List<Person> people) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Person p : people) {
                bw.write(p.toDataString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //2) 일반 텍스트 파일 읽기
    public static List<Person> readTextFile(String filename) {
        List<Person> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Person p = Person.fromDataString(line);
                list.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //3) CSV 파일 쓰기 (텍스트와 유사하지만 확장자로 csv로 가정)
    public static void writeCSV(String filename, List<Person> people) {
        //실제 CSV 처리 라이브러리 사용 가능 (opencsv 등)
        //여기서는 단순히 ','로 구분해 쓰는 예시
        writeTextFile(filename, people);
    }

    //4) CSV 파일 읽기
    public static List<Person> readCSV(String filename) {
        //내용은 readTextFile과 동일, CSV 처리를 위해 별도 라이브러리 사용 가능
        return readTextFile(filename);
    }

    //5) 이진 파일(직렬화) 쓰기
    public static void writeBinary(String filename, List<Person> people) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(people);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //6) 이진 파일(직렬화) 읽기
    @SuppressWarnings("unckecked")
    public static List<Person> readBinary(String filename) {
        List<Person> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            list = (List<Person>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    //7) JSON 파일 쓰기 (Gson 사용 예시)
    public static void writeJson(String filename, List<Person> people) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(people);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //8) JSON 파일 읽기 (Gson 사용 예시)
    public static List<Person> readJson(String filename) {
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return gson.fromJson(sb.toString(), new TypeToken<List<Person>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 9) XML 파일 쓰기 (단순 예시, 실제는 JAXB나 JacksonXml 등 사용)
    public static void writeXml(String filename, List<Person> people) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("<people>");
            for (Person p : people) {
                bw.write("<person>");
                bw.write("<name>" + p.getName() + "</name>");
                bw.write("<age>" + p.getAge() + "</age>");
                bw.write("</person>");
            }
            bw.write("</people>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 10) XML 파일 읽기 (단순 파싱 예시)
    public static List<Person> readXml(String filename) {
        // 실제 XML 파서는 DOM, SAX, StAX, JAXB 등 사용 가능
        List<Person> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String all = sb.toString();
            // <person> ~ </person> 블록을 찾아 파싱
            String[] personBlocks = all.split("</person>");
            for (String block : personBlocks) {
                if (block.contains("<person>")) {
                    // 이름 추출
                    String name = extractXmlValue(block, "name");
                    // 나이 추출
                    String ageStr = extractXmlValue(block, "age");
                    Person p = new Person(name, Integer.parseInt(ageStr));
                    list.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // XML 태그값 추출 간단 유틸
    private static String extractXmlValue(String block, String tag) {
        // <tag> 와 </tag> 사이 문자열 추출
        int start = block.indexOf("<" + tag + ">") + tag.length() + 2;
        int end = block.indexOf("</" + tag + ">");
        return block.substring(start, end);
    }

    //11) YAML 파일 쓰기 (단순 예시, 실제로는 SnakeYAML 등 사용)
    public static void writeYaml(String filename, List<Person> people) {
        //실제 사용 시 SnakeYAML 라이브러리 필요
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Person p : people) {
                bw.write("- name: " + p.getName());
                bw.newLine();
                bw.write("  age: " + p.getAge());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //12) YAML 파일 읽기 (단순 예시)
    public static List<Person> readYaml(String filename) {
        //실제 사용 시 SnakeYAML 라이브러리로 파싱
        List<Person> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String name = null;
            String age = null;
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("- name:")) {
                    name = line.substring(line.indexOf(":") + 1).trim();
                } else if (line.startsWith("age:") || line.startsWith("  age:")) {
                    age = line.substring(line.indexOf(":") + 1).trim();
                    if (name != null && age != null) {
                        list.add(new Person(name, Integer.parseInt(age)));
                        name = null;
                        age = null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //13) Properties 파일 쓰기 (java.util.Properties 사용)
    public static void writeProperties(String filename, List<Person> people) {
        Properties props = new Properties();
        //예시로 personX.name, personX.age 저장
        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            props.setProperty("person" + i + ".name", p.getName());
            props.setProperty("person" + i + ".age", String.valueOf(p.getAge()));
        }
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            props.store(fos, "Person Properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //14) Properties 파일 읽기
    public static List<Person> readProperties(String filename) {
        List<Person> list = new ArrayList<>();
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(filename)) {
            props.load(fis);
            //person0.name, person0.age, person1.name ...
            //키 집합을 순회하며 person 인덱스 찾기
            int index = 0;
            while (true) {
                String name = props.getProperty("person" + index + ".name");
                String age = props.getProperty("person" + index + ".age");
                if (name == null || age == null) {
                    break;
                }
                list.add(new Person(name, Integer.parseInt(age)));
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //15) HTML 파일 쓰기 (간단하게 태그 작성)
    public static void writeHtml(String filename, List<Person> people) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("<html><body>");
            bw.newLine();
            bw.write("<h1>Person Lsit</h1>");
            bw.newLine();
            bw.write("<ul>");
            bw.newLine();
            for (Person p : people) {
                bw.write("<li>" + p.getName() + " (" + p.getAge() + ")</li>");
                bw.newLine();
            }
            bw.write("</ul>");
            bw.newLine();
            bw.write("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //16) HTML 파일 읽기 (아주 간단한 문자열 파싱 예시)
    public static List<Person> readHtml(String filename) {
        //현실적으로 HTML 파싱은 Jsoup 등 라이브러리 사용
        //여기서는 <li>... </li> 태그 기준으로 이름, 나이 추출
        List<Person> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String all = sb.toString();
            String[] liTags = all.split("</li>");
            for (String li : liTags) {
                if (li.contains("<li>")) {
                    String content = li.substring(li.indexOf("<li>") + 4).trim();
                    //예: "홍길동 (30)"
                    if (content.contains("(") && content.contains(")")) {
                        String name = content.substring(0, content.indexOf("(")).trim();
                        String ageStr = content.substring(content.indexOf("(") + 1, content.indexOf(")")).trim();
                        list.add(new Person(name, Integer.parseInt(ageStr)));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //17) Markdown 파일 쓰기 (간단 예시)
    public static void writeMarkdown(String filename, List<Person> people) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("# Person List");
            bw.newLine();
            for (Person p : people) {
                bw.write("- **" + p.getName() + "**, " + p.getAge() + "살");
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //18) Markdown 파일 읽기 (단순 예시)
    public static List<Person> readMarkdown(String filename) {
        //예: "- **홍길동**, 30살"
        List<Person> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("- **")) {
                    int startName = line.indexOf("**") + 2;
                    int endName = line.indexOf("**", startName);
                    String name = line.substring(startName, endName);
                    String rest = line.substring(endName + 2).trim();
                    //", 30살" -> 나이 부분만 정수로 파싱
                    if (rest.startsWith(",")) {
                        rest = rest.substring(1).trim();
                        String ageStr = rest.replace("살", "").trim();
                        list.add(new Person(name, Integer.parseInt(ageStr)));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //19) ZIP 압축 쓰기 (텍스트로 묶는 간단 예시)
    public static void writeZip(String filename, List<Person> people) {
        //ZIP 안에 "persons.txt" 라는 파일을 넣는 예시
        // 철수는 밥을 먹었습니다. 영희는 밥을 먹었습니다.
        // 철수는 밥을 먹었습니다(A1). 영희는 (A1)
        // 0x11234599234999fafdddfagdfadfgdffafd
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(filename))) {
            ZipEntry entry = new ZipEntry("persons.txt");
            zos.putNextEntry(entry);
            //person 정보를 문자열로 만들어 ZIP 내에 기록
            StringBuilder sb = new StringBuilder();
            for (Person p : people) {
                sb.append(p.toDataString()).append(System.lineSeparator());
            }
            byte[] data = sb.toString().getBytes();
            zos.write(data, 0 , data.length);
            zos.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //20) ZIP 압축 읽기 (텍스트 파일 하나 있다고 가정)
    public static List<Person> readZip(String filename) {
        List<Person> list = new ArrayList<>();
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(filename))) {
            ZipEntry entry;
            while ((entry = zis. getNextEntry()) != null) {
                if (entry.getName().equals("persons.txt")) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(zis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        Person p = Person.fromDataString(line);
                        list.add(p);
                    }
                    zis.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
