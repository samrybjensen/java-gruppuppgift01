#!/bin/bash

BASE_URL="http://localhost:8080/books"

# Function to add a book
add_book() {
  NAME=$1
  GENRE=$2

  curl -s -X POST "$BASE_URL" \
    -H "Content-Type: application/json" \
    -d "{\"name\": \"$NAME\", \"genre\": \"$GENRE\"}"
  echo -e "\nBook added: $NAME ($GENRE)"
}

# Function to get all books
get_books() {
  echo "Fetching all books..."
  curl -s -X GET "$BASE_URL"
  echo -e "\n"
}

# Example usage
add_book "The Hobbit" "Fantasy"
add_book "Harry Potter" "Fantasy"
get_books
