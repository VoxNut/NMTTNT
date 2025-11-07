# Lab 6: Informed Search - Bài toán 8 Quân Hậu (8-Queens Problem)

## Giới thiệu

Bài toán 8 Quân Hậu: Đặt 8 quân hậu trên bàn cờ 8×8 sao cho không quân hậu nào tấn công được quân hậu khác (không cùng hàng, cột, hoặc đường chéo).

## Cấu trúc dự án

```
Lab_6/
├── Queen.java                    # Lớp biểu diễn trạng thái bàn cờ
├── HillClimbingSearch.java       # Thuật toán Leo đồi
├── SimulatedAnnealingSearch.java # Thuật toán Luyện kim mô phỏng
├── GeneticAlgorithm.java         # Thuật toán Di truyền
├── Test.java                     # Chương trình test đơn lẻ
└── MultipleRunsTest.java         # Chương trình test nhiều lần
```

## Các thuật toán đã triển khai

### 1. Hill Climbing (Leo đồi)

**Đặc điểm:**

- Tìm kiếm cục bộ, luôn di chuyển đến trạng thái tốt nhất lân cận
- Nhanh nhưng dễ bị mắc kẹt ở cực trị cục bộ
- Không đảm bảo tìm được nghiệm

**Cách hoạt động:**

1. Bắt đầu từ trạng thái ngẫu nhiên
2. Sinh tất cả trạng thái lân cận (di chuyển mỗi quân hậu đến hàng khác)
3. Chọn trạng thái tốt nhất (ít xung đột nhất)
4. Nếu không có trạng thái nào tốt hơn → dừng (local minimum)

### 2. Simulated Annealing (Luyện kim mô phỏng)

**Đặc điểm:**

- Cải tiến Hill Climbing, cho phép di chuyển đến trạng thái xấu hơn với xác suất
- Xác suất giảm dần theo thời gian (nhiệt độ giảm)
- Tỷ lệ thành công cao hơn Hill Climbing

**Cách hoạt động:**

1. Bắt đầu từ trạng thái ngẫu nhiên với nhiệt độ cao
2. Sinh trạng thái lân cận ngẫu nhiên
3. Chấp nhận nếu:
   - Trạng thái tốt hơn → luôn chấp nhận
   - Trạng thái xấu hơn → chấp nhận với xác suất `e^(-delta/T)`
4. Giảm nhiệt độ theo hệ số làm lạnh
5. Lặp lại cho đến khi tìm được nghiệm hoặc nhiệt độ quá thấp

**Tham số:**

- `initialTemperature`: 1000.0 (nhiệt độ ban đầu)
- `coolingRate`: 0.995 (tốc độ làm lạnh)
- `maxIterations`: 10000 (số lần lặp tối đa)

### 3. Genetic Algorithm (Thuật toán Di truyền)

**Đặc điểm:**

- Mô phỏng quá trình tiến hóa tự nhiên
- Duy trì một quần thể các cá thể (trạng thái)
- Tỷ lệ thành công rất cao

**Cách hoạt động:**

1. **Khởi tạo quần thể:** Tạo ngẫu nhiên N cá thể
2. **Đánh giá:** Tính số xung đột cho mỗi cá thể
3. **Chọn lọc:** Chọn cha mẹ tốt nhất (tournament selection)
4. **Lai ghép:** Kết hợp 2 cha mẹ tạo con (single-point crossover)
5. **Đột biến:** Thay đổi ngẫu nhiên vị trí quân hậu với xác suất nhỏ
6. **Thay thế:** Quần thể mới thay thế quần thể cũ
7. Lặp lại cho đến khi tìm được nghiệm

**Tham số:**

- `populationSize`: 100 (kích thước quần thể)
- `mutationRate`: 0.1 (tỷ lệ đột biến 10%)
- `maxGenerations`: 1000 (số thế hệ tối đa)

## Kết quả so sánh

### Test đơn lẻ (1 lần chạy):

| Thuật toán          | Thành công | Thời gian | Xung đột |
| ------------------- | ---------- | --------- | -------- |
| Hill Climbing       | ❌ NO      | 15 ms     | 2        |
| Simulated Annealing | ✅ YES     | 40 ms     | 0        |
| Genetic Algorithm   | ✅ YES     | 11 ms     | 0        |

### Test nhiều lần (10 runs):

| Thuật toán          | Tỷ lệ thành công | Thời gian TB |
| ------------------- | ---------------- | ------------ |
| Hill Climbing       | ~10-30%          | ~5-15 ms     |
| Simulated Annealing | ~80-95%          | ~30-50 ms    |
| Genetic Algorithm   | ~90-100%         | ~10-20 ms    |

## Phân tích

### Hill Climbing:

**Ưu điểm:**

- Rất nhanh
- Đơn giản, dễ cài đặt

**Nhược điểm:**

- Thường bị mắc kẹt ở local minimum
- Tỷ lệ thành công thấp
- Phụ thuộc nhiều vào trạng thái ban đầu

### Simulated Annealing:

**Ưu điểm:**

- Tránh được local minimum
- Tỷ lệ thành công cao (80-95%)
- Ổn định hơn Hill Climbing

**Nhược điểm:**

- Chậm hơn Hill Climbing và GA
- Cần điều chỉnh tham số nhiệt độ và cooling rate

### Genetic Algorithm:

**Ưu điểm:**

- Tỷ lệ thành công rất cao (90-100%)
- Nhanh, hiệu quả
- Khám phá không gian tìm kiếm tốt

**Nhược điểm:**

- Phức tạp hơn trong cài đặt
- Cần nhiều bộ nhớ (lưu quần thể)
- Cần điều chỉnh nhiều tham số

## Kết luận

**Thuật toán tốt nhất:** **Genetic Algorithm**

- Tỷ lệ thành công cao nhất
- Thời gian chấp nhận được
- Ổn định và đáng tin cậy

**Khuyến nghị:**

- Dùng GA cho bài toán cần độ chính xác cao
- Dùng Hill Climbing cho bài toán cần tốc độ, chấp nhận sai số
- Dùng Simulated Annealing khi cần cân bằng giữa chính xác và tốc độ

## Cách chạy

```bash
# Chạy test đơn lẻ (1 lần)
java Lab_6.Test

# Chạy test nhiều lần (10 runs)
java Lab_6.MultipleRunsTest
```

## Ví dụ nghiệm đúng (0 conflicts)

```
. . . . . . Q .    (cột 0: quân hậu ở hàng 6)
Q . . . . . . .    (cột 1: quân hậu ở hàng 0)
. . Q . . . . .    (cột 2: quân hậu ở hàng 2)
. . . . . . . Q    (cột 3: quân hậu ở hàng 7)
. . . . . Q . .    (cột 4: quân hậu ở hàng 5)
. . . Q . . . .    (cột 5: quân hậu ở hàng 3)
. Q . . . . . .    (cột 6: quân hậu ở hàng 1)
. . . . Q . . .    (cột 7: quân hậu ở hàng 4)
```

Không có quân hậu nào tấn công được quân hậu khác!
