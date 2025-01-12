FROM ubuntu:latest
LABEL authors="kimjh"

ENTRYPOINT ["top", "-b"]