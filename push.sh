#!/bin/bash

# Ask for commit message
echo "Enter commit message:"
read commitMessage

# Add all files
git add .

# Commit with the entered message
git commit -m "$commitMessage"

# Push to the current branch
git push

echo "✅ Code pushed successfully!"
