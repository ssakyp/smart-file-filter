# Smart File Filter

A command-line Java utility for filtering the content of input files based on data types.

The program reads multiple text files containing mixed data types (integers, floating-point numbers, and strings) and writes them into separate output files by type.

---

## ✅ Features

- Detects and separates:
    - Integers
    - Floating-point numbers
    - Strings
- Supports multiple input files
- CLI options for:
    - `-o` — specify output directory
    - `-p` — prefix for output filenames
    - `-a` — append mode (instead of overwrite)
    - `-s` — show short statistics (counts only)
    - `-f` — show full statistics (min, max, average, lengths, etc.)
- Creates only the necessary output files (if no data of that type — file is not created)
- Error-resistant: continues processing even if some files contain invalid or unreadable lines

---

## 🚀 Getting Started

### 📦 Prerequisites

- Java 17 or later
- Maven 3.6+ (if you're using Maven)

---

### 🔧 Build the project

```bash
mvn clean package
```

This will create a `.jar` file in the `target/` directory.

---

### ▶️ Run the program

```bash
java -jar target/smart-file-filter.jar -s -p sample- in1.txt in2.txt
```

### 🔁 Example output files:

- `sample-integers.txt`
- `sample-floats.txt`
- `sample-strings.txt`

---

### 🛠 Command-line options

| Option     | Description                                      |
|------------|--------------------------------------------------|
| `-o`       | Output directory                                 |
| `-p`       | Prefix for output filenames                      |
| `-a`       | Append mode (instead of overwrite)               |
| `-s`       | Show short statistics (counts only)              |
| `-f`       | Show full statistics                             |

> If neither `-s` nor `-f` is used, no statistics will be printed.

---

### 📊 Sample Statistics Output

```
Integers: count = 3
Floats: count = 2, min = -0.1, max = 3.14, sum = 3.04, avg = 1.52
Strings: count = 4, shortest = 6 chars, longest = 42 chars
```

---

## 📂 Example Input Files

`in1.txt`
```
Lorem ipsum
45
3.1415
-0.001
тестовое задание
```

`in2.txt`
```
Long
1234567890123456789
1.528535047E-25
```

---

## 📚 Technologies Used

- Java 17
- Maven
- Apache Commons CLI (for argument parsing)

---

## 🧑‍💻 Author

Sultan Sakyp  
[GitHub](https://github.com/ssakyp)

---
