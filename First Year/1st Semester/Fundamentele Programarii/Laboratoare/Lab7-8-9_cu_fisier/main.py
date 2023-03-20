from domain.validators import BookValidator, ClientValidator, RentItemValidator
from repository.book_repo import BookRepoFile
from repository.client_repo import ClientRepoFile
from repository.rent_item_repo import RentItemRepoFile
from service.book_service import BookService
from service.client_service import ClientService
from service.rent_item_service import RentItemService
from ui.console import Console

val_book = BookValidator()
repo_book = BookRepoFile('data/books.txt')
srv_book = BookService(repo_book, val_book)

val_client = ClientValidator()
repo_client = ClientRepoFile('data/clients.txt')
srv_client = ClientService(repo_client, val_client)

rent_item_repo = RentItemRepoFile('data/rent_items.txt')
rent_item_val = RentItemValidator()
srv_rent_item = RentItemService(rent_item_repo, rent_item_val, repo_book, repo_client)

ui = Console(srv_book, srv_client, srv_rent_item)
ui.show_ui()
