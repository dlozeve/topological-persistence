library(tidyverse)

timings <- read_csv("timings.csv",
                    col_names=c("filtration","n_simplices","creation","reduction"),
                    skip=1)
timings

ggplot(timings, aes(x=n_simplices, y=reduction)) +
    #geom_line(aes(x=n_simplices, y=creation)) +
    geom_line() + geom_point() +
    ggsave("timings.png", width=15, height=6)


