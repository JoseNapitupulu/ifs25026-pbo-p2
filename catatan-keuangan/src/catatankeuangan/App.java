package catatankeuangan;

import adapter.presenter.TransactionPresenter;
import adapter.repository.TransactionRepository;
import framework.view.TransactionView;
import usecase.TransactionUseCase;

/** Entry point aplikasi Catatan Keuangan. */
public class App {
    public static void main(String[] args) {
        TransactionRepository repository = new TransactionRepository();
        TransactionUseCase useCase = new TransactionUseCase(repository);
        TransactionPresenter presenter = new TransactionPresenter();
        new TransactionView(useCase, presenter).show();
    }
}
