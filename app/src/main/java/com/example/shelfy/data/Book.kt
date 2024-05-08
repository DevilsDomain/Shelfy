package com.example.shelfy.data

import com.example.shelfy.R

data class Book(
    val title: String,
    val author: String,
    val synopsis: String,
    val imageResId: Int
)

object FakeData {
    val bookList = listOf(
        Book(
            title = "Dungeons and Drama",
            author = "Kristy Boyce",
            synopsis = "The Great Gatsby is a story of the fabulously wealthy Jay Gatsby and his love for the beautiful Daisy Buchanan, of lavish parties on Long Island at a time when The New York Times noted “gin was the national drink and sex the national obsession,” it is an exquisitely crafted tale of America in the 1920s.",
            imageResId = R.drawable.book_image_1
        ),
        Book(
            title = "Iron Flame",
            author = "Rebecca Yarros",
            synopsis = "To Kill a Mockingbird is a novel by Harper Lee published in 1960. Instantly successful, widely read in high schools and middle schools in the United States, it has become a classic of modern American literature, winning the Pulitzer Prize.",
            imageResId = R.drawable.book_image_2
        ),
        Book(
            title = "Red Rising",
            author = "Pierce Brown",
            synopsis = "Nineteen Eighty-Four: A Novel, often published as 1984, is a dystopian social science fiction novel by English novelist George Orwell. It was published on 8 June 1949 by Secker & Warburg as Orwell's ninth and final book completed in his lifetime.",
            imageResId = R.drawable.book_image_3
        ),
        Book(
            title = "12 chairs",
            author = "Ilya & Ilf",
            synopsis = "Pride and Prejudice is a romantic novel of manners written by Jane Austen in 1813. The novel follows the character development of Elizabeth Bennet, the dynamic protagonist of the book who learns about the repercussions of hasty judgments and comes to appreciate the difference between superficial goodness and actual goodness.",
            imageResId = R.drawable.book_image_4
        ),
        Book(
            title = "Kinyas and Kayra",
            author = "Hakan gunday",
            synopsis = "The Catcher in the Rye is a novel by J. D. Salinger, partially published in serial form in 1945–1946 and as a novel in 1951. It was originally intended for adults but is often read by adolescents for its themes of angst, alienation, and as a critique on superficiality in society.",
            imageResId = R.drawable.book_image_5
        )
    )
}
