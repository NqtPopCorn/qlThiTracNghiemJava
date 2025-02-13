-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping data for table thitracnghiem.answers: ~19 rows (approximately)
INSERT INTO `answers` (`awID`, `qID`, `awContent`, `awPictures`, `isRight`, `awStatus`) VALUES
	(1, 4, 'Paris', '/pictures/example.png', 1, 1),
	(2, 4, 'London', NULL, 0, 1),
	(3, 4, 'Berlin', NULL, 0, 1),
	(4, 5, '19', NULL, 0, 1),
	(5, 5, '24', NULL, 0, 1),
	(6, 5, '19', NULL, 0, 1),
	(7, 5, '17', NULL, 1, 1),
	(8, 6, 'Harper Lee', NULL, 1, 1),
	(9, 6, 'J.K. Rowling', NULL, 0, 1),
	(10, 6, 'George Orwell', NULL, 0, 1),
	(11, 7, '299,792,458 m/s', NULL, 1, 1),
	(12, 7, '150,000,000 m/s', NULL, 0, 1),
	(13, 7, '3,000,000 m/s', NULL, 0, 1),
	(14, 8, 'Python, Java, C++', NULL, 1, 1),
	(15, 8, 'HTML, CSS, JavaScript', NULL, 0, 1),
	(16, 8, 'Swift, Ruby, PHP', NULL, 1, 1),
	(17, 1, 'Sorry', NULL, 1, 1),
	(18, 3, 'So much', NULL, 1, 1),
	(19, 2, "Cuz you\'re fucking annoying", NULL, 1, 1);

-- Dumping data for table thitracnghiem.exams: ~2 rows (approximately)
INSERT INTO `exams` (`exCode`, `testCode`, `exOrder`, `ex_quesIDs`) VALUES
	('EX001', 'TST001', '1', '[1, 3, 6, 7]'),
	('EX002', 'TST003', '2', '[1, 6, 7]');

-- Dumping data for table thitracnghiem.logs: ~2 rows (approximately)
INSERT INTO `logs` (`logID`, `logContent`, `logUserID`, `logExID`, `logDate`) VALUES
	(1, '[10,11]', 2, 'EX001', '2025-02-13 15:41:26'),
	(2, '[17, 10, 11]', 2, 'EX002', '2025-02-13 15:48:50');

-- Dumping data for table thitracnghiem.questions: ~8 rows (approximately)
INSERT INTO `questions` (`qID`, `qContent`, `qPictures`, `qTopicID`, `qLevel`, `qStatus`) VALUES
	(1, 'Why are you gay?', '/pictures/example.png', 3, '1', 1),
	(2, 'Why are you so mad?', '/pictures/example.png', 7, '1', 1),
	(3, 'How dumb are you?', '/pictures/example.png', 3, '2', 1),
	(4, 'What is the capital of France?', '/pictures/example.png', 10, '1', 1),
	(5, 'Solve: 5 + 7 × 2', '/pictures/example.png', 9, '2', 1),
	(6, 'Who wrote "To Kill a Mockingbird"?', '/pictures/example.png', 3, '2', 1),
	(7, 'What is the speed of light?', '/pictures/example.png', 3, '3', 1),
	(8, 'Name three programming languages.', '/pictures/example.png', 7, '2', 1);

-- Dumping data for table thitracnghiem.result: ~2 rows (approximately)
INSERT INTO `result` (`rs_num`, `userID`, `exCode`, `rs_answers`, `rs_mark`) VALUES
	(1, 2, 'EX001', '[10,11]', 0.00),
	(2, 2, 'EX002', '[17,10, 11]', 10.00);

-- Dumping data for table thitracnghiem.test: ~5 rows (approximately)
INSERT INTO `test` (`testID`, `testCode`, `testTitle`, `testTime`, `tpID`, `num_easy`, `num_medium`, `num_diff`, `testLimit`, `testDate`, `testStatus`) VALUES
	(1, 'TST001', 'Basic Math Test', 60, 3, 10, 5, 2, 1, '2025-02-15', 1),
	(2, 'TST002', 'Advanced Programming', 90, 7, 5, 10, 5, 1, '2025-02-16', 1),
	(3, 'TST003', 'General Knowledge Quiz', 45, 3, 15, 5, 1, 1, '2025-02-17', 1),
	(4, 'TST004', 'Physics Level 1', 60, 9, 10, 5, 5, 1, '2025-02-18', 1),
	(5, 'TST005', 'History and Geography', 50, 7, 12, 6, 3, 1, '2025-02-19', 1);

-- Dumping data for table thitracnghiem.topics: ~10 rows (approximately)
INSERT INTO `topics` (`tpID`, `tpTitle`, `tpParent`, `tpStatus`) VALUES
	(1, 'Khoa Học Tự Nhiên', NULL, 1),
	(2, 'Khoa Học Xã Hội', NULL, 1),
	(3, 'Công Nghệ Thông Tin', 1, 1),
	(4, 'Hóa Học', 1, 1),
	(5, 'Quản trị kinh doanh', 2, 1),
	(6, 'Việt Nam học', 2, 1),
	(7, 'Cơ sở lập trình', 3, 1),
	(8, 'Polyme', 4, 1),
	(9, 'Nhập môn tâm lý', 5, 1),
	(10, 'Lịch sử Việt Nam', 6, 1);

-- Dumping data for table thitracnghiem.users: ~2 rows (approximately)
INSERT INTO `users` (`userID`, `userName`, `userEmail`, `userPassword`, `userFullName`, `isAdmin`) VALUES
	(1, 'truong', 'truong@admin.com', '$2a$10$0EPi6Pp5MhaJ576kKpF9lOvjWVfhN3AQN56EPPDCjjmy0KWXbwc0S', 'Ngo Quang Truong', 1),
	(2, 'trung', 'trung@sv.com', '$2a$10$pTh/PbOoWLN7zKWqUdLZge6mlu5L3jcNkv3SQVxczAnkLZIo8kCdy', 'Tang Thanh Trung', 0);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
