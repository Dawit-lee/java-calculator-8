package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        // 테스트를 위해 try-catch는 제거된 상태를 유지합니다.
        int result = calculate(input);
        System.out.println("결과 : " + result);
    }

    // --- 계산 로직 (기존 StringCalculator의 내용) ---

    public static int calculate(String text) {
        // 기능 1: 비어있거나 null이면 0 반환
        if (text == null || text.isBlank()) {
            return 0;
        }

        String delimiter = "[,:]"; // 기본 구분자
        String numbersText = text;

        // 기능 3: 커스텀 구분자 처리 로직
        Matcher matcher = Pattern.compile("//(.)\\\\n(.*)").matcher(text);
        if (matcher.find()) {
            delimiter = Pattern.quote(matcher.group(1));
            numbersText = matcher.group(2);
        }

        // 기능 2: 구분자로 숫자 분리
        String[] numbers = numbersText.split(delimiter);
        return sumNumbers(numbers);
    }

    private static int sumNumbers(String[] numbers) {
        int sum = 0;
        for (String numberStr : numbers) {
            if (numberStr.isEmpty()) { // split 후 빈 문자열이 생기는 경우 건너뛰기
                continue;
            }
            try {
                int number = Integer.parseInt(numberStr);

                // 기능 4: 음수 예외 처리
                if (number < 0) {
                    throw new IllegalArgumentException("입력값에 음수가 포함될 수 없습니다.");
                }
                sum += number;
            } catch (NumberFormatException e) {
                // 기능 4: 숫자가 아닌 값 예외 처리
                throw new IllegalArgumentException("입력값에 숫자가 아닌 문자가 포함되어 있습니다.");
            }
        }
        return sum;
    }
}

