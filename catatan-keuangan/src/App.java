import adapter.presenter.TransactionPresenter;
import adapter.repository.TransactionRepository;
import domain.repository.ITransactionRepository;
import framework.view.TransactionView;
import usecase.TransactionUseCase;

/**
 * Titik masuk aplikasi (Composition Root).
 * Semua dependency antar layer disusun di sini — satu-satunya tempat
 * yang mengetahui implementasi konkret dari setiap interface.
 */
public class App {
    public static void main(String[] args) {
        // Layer adapter: implementasi konkret repository (penyimpanan in-memory)
        ITransactionRepository transactionRepository = new TransactionRepository();

        // Layer usecase: logika bisnis, hanya bergantung pada interface repository
        TransactionUseCase transactionUseCase = new TransactionUseCase(transactionRepository);

        // Layer adapter: presenter untuk memformat output ke layar
        TransactionPresenter transactionPresenter = new TransactionPresenter();

        // Layer framework: UI konsol yang menerima input user
        TransactionView transactionView = new TransactionView(transactionUseCase, transactionPresenter);

        // Menjalankan loop menu utama aplikasi
        transactionView.show();
    }
}
