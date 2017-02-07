library(tidyverse)

barcode <- read_delim("../filtration_C_barcode.txt", delim=' ',
                      col_names=c('dim', 'inf', 'sup'))
barcode

ggplot(barcode, aes(ymin=inf, ymax=sup, x=dim, y=dim)) +
    geom_linerange(position=position_jitter(.3)) +
    coord_flip() +
    scale_x_reverse()


