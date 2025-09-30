package org.example;

public class ExpressionParser {

    public Expression parse(String str) {
        str = str.trim();

        // Попытка 1: Проверить, не является ли строка числом
        try {
            double value = Double.parseDouble(str);
            return new Constant(value);
        } catch (NumberFormatException e) {
            // Если не число, идем дальше
        }

        // Попытка 2: Проверить, не является ли строка переменной
        if (str.matches("[a-zA-Z]+")) {
            return new Variable(str);
        }

        // Попытка 3: Разбор сложного выражения в скобках
        if (str.startsWith("(") && str.endsWith(")")) {
            // Убираем внешние скобки
            String sub = str.substring(1, str.length() - 1);

            int balance = 0; // Счетчик баланса скобок
            int mainOpIndex = -1; // Индекс главного оператора (+ или -)
            int highOpIndex = -1; // Индекс второстепенного оператора (* или /)

            // Ищем главный оператор, идя по строке С КОНЦА
            for (int i = sub.length() - 1; i >= 0; i--) {
                char c = sub.charAt(i);
                if (c == ')') {
                    balance++;
                } else if (c == '(') {
                    balance--;
                }

                // Если мы не внутри других скобок (баланс = 0)
                if (balance == 0) {
                    // Операторы + и - имеют самый низкий приоритет, ищем их в первую очередь
                    if (c == '+' || c == '-') {
                        mainOpIndex = i;
                        break; // Нашли, выходим
                    }
                    // Запоминаем последний оператор * или /, если + или - не найдены
                    if ((c == '*' || c == '/') && highOpIndex == -1) {
                        highOpIndex = i;
                    }
                }
            }

            int splitIndex = (mainOpIndex != -1) ? mainOpIndex : highOpIndex;

            if (splitIndex != -1) {
                // Рекурсивно вызываем parse для левой и правой части
                Expression left = parse(sub.substring(0, splitIndex));
                Expression right = parse(sub.substring(splitIndex + 1));

                // Создаем нужный объект операции
                switch (sub.charAt(splitIndex)) {
                    case '+': return new Add(left, right);
                    case '-': return new Sub(left, right);
                    case '*': return new Mul(left, right);
                    case '/': return new Div(left, right);
                }
            }
        }

        throw new IllegalArgumentException("Cannot parse expression: " + str);
    }
}
