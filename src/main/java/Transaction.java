import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An immutable record of a single deposit or withdrawal.
 * Using a record here means the compiler generates the constructor,
 * getters, equals/hashCode and toString for us — no boilerplate.
 */
public record Transaction(TransactionType type, double amount, double resultingBalance, LocalDateTime timestamp) {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    /** Convenience constructor that timestamps the transaction as "now". */
    public Transaction(TransactionType type, double amount, double resultingBalance) {
        this(type, amount, resultingBalance, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s of R%.2f -> balance R%.2f",
                timestamp.format(FORMAT), type, amount, resultingBalance);
    }
}
