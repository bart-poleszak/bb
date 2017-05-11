# bb
Bitbucket (including Bitbucket Server) doesn't have a way to search for commit by its hash. That's why I've wrote this simple bash script.

Thanks to a similar URL scheme, `bb` also works with GitHub repositories.

## How to use?

Copy `bb` to a directory included in the environment path. Call

```
bb <commit-hash>
```

inside the repository directory.
