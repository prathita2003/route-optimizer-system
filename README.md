# Route Optimization System (Graph-Based Shortest Path Finder)

## Overview

The Route Optimization System is a full-stack application designed to compute the shortest path between locations using graph-based algorithms. The system models locations as nodes and connections as weighted edges, enabling efficient route computation and algorithmic comparison.

This project emphasizes core concepts in graph theory, algorithm design, and system integration, with practical relevance to navigation, logistics, and network optimization.

---

## Key Features

* Dynamic creation of nodes (locations)
* Addition of weighted edges between nodes
* Shortest path computation using:

  * Dijkstra’s Algorithm
  * A* Algorithm
* Algorithm comparison based on:

  * Path
  * Total distance
  * Execution time
* RESTful backend services
* Interactive frontend interface
* Cloud deployment on AWS EC2

---

## Algorithms Implemented

### Dijkstra’s Algorithm

* Computes the shortest path in weighted graphs with non-negative edges
* Uses a priority queue (min-heap)
* Time Complexity: O((V + E) log V)

### A* Algorithm

* Extension of Dijkstra’s algorithm with heuristic optimization
* Uses Euclidean distance as a heuristic
* Improves performance for large graphs by reducing search space

---

## System Architecture

Frontend (Angular) → REST API (Spring Boot) → Graph Processing Engine

* Angular handles user interaction and visualization
* Spring Boot exposes REST endpoints
* Core logic implemented using graph data structures and algorithms

---

## Technology Stack

**Backend**

* Java
* Spring Boot
* REST APIs

**Frontend**

* Angular
* TypeScript
* HTML / CSS

**Deployment**

* AWS EC2 (Ubuntu)
* Java 21 Runtime Environment

---

## API Endpoints

| Method | Endpoint                 | Description                    |
| ------ | ------------------------ | ------------------------------ |
| POST   | /api/add-node            | Add a node with coordinates    |
| POST   | /api/add-edge            | Add a weighted edge            |
| GET    | /api/astar?src=A&dst=B   | Compute shortest path using A* |
| GET    | /api/compare?src=A&dst=B | Compare Dijkstra vs A*         |

---

## Sample Output

```json
{
  "path": ["A", "C", "D", "F"],
  "distance": 12
}
```

---

## Screenshots

### Application Interface

<img width="1365" height="596" alt="image" src="https://github.com/user-attachments/assets/cc12568a-5695-4052-95d2-9a197b1bf2e7" />


### Algorithm Comparison

<img width="649" height="341" alt="image" src="https://github.com/user-attachments/assets/fef50c97-181f-4229-92d2-661bc342747d" />


### Result Display

<img width="976" height="588" alt="image" src="https://github.com/user-attachments/assets/4bdd1759-cb5b-44bf-88fe-d8653c54047c" />

---

## Local Setup Instructions

### 1. Clone Repository

```
git clone <repository-url>
cd route-optimizer-system
```

### 2. Run Backend

```
mvn spring-boot:run
```

### 3. Run Frontend

```
cd route-frontend
ng serve
```

---

## Cloud Deployment (AWS EC2)

The application is deployed on an AWS EC2 instance using a unified backend deployment model:

* Angular frontend is built using `ng build`
* Build artifacts are copied into:

  ```
  src/main/resources/static/
  ```
* Spring Boot packages frontend and backend into a single executable JAR
* The application is deployed and run on EC2 using:

```
nohup java -jar routeoptimizer-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

* The application is accessible via:

  ```
  http://13.223.51.150:8080
  ```

This approach ensures a single deployment unit and simplifies hosting and scaling.

---

## Key Contributions and Learning Outcomes

* Implementation of fundamental graph algorithms in a production-like system
* Comparative analysis of algorithm efficiency (Dijkstra vs A*)
* Design of scalable REST APIs using Spring Boot
* Integration of frontend and backend into a unified deployment
* Hands-on experience with AWS cloud infrastructure
* End-to-end system development from algorithm design to deployment

---

## Future Enhancements

* Graph visualization using interactive libraries
* Integration with real-world map APIs (e.g., Google Maps)
* Persistent storage using relational databases
* User authentication and session management
* Containerization using Docker

---

## Author

Prathita Kumar Madhusudhana
