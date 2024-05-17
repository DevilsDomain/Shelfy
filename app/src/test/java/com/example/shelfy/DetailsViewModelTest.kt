import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.example.shelfy.db.ShelfRepository
import com.example.shelfy.screens.details.DetailsViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.*

class DetailsViewModelTest {

    private lateinit var repository: ShelfRepository
    private lateinit var viewModel: DetailsViewModel
    private lateinit var application: Application

    @Before
    fun setUp() {
        repository = mock(ShelfRepository::class.java)
        application = ApplicationProvider.getApplicationContext()
        viewModel = DetailsViewModel(application, repository)
    }

    @Test
    fun testUpdateBookStatusToFinished() {
        val bookId = "1"
        val status = "Finished"

        viewModel.updateBookStatus(bookId, status)

        verify(repository).updateBookStatus(bookId, status)
        verify(repository).updateFinishedDate(eq(bookId), anyLong())
    }
}
