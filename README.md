# NGSSets
A JAVA API aimed to manage genomic datasets (Bed, VCF, FASTA, FASTQ, BAM/SAM). You can do many common tasks in genomic with only one API.

### DNA sequence dataset
This is the most common dataset in Genomics, represented by FASTA format. It consists of a String characters (that represents a DNA sequence) and an ID. With this API you can import and export FASTA files, renames the IDs, entries, etc.

### Sequencing datasets
Next generation sequencing (NGS) is one of the most widely used technologies in genomics. The format used in NGS data is FASTQ, a variation of FASTA, that include some extra information about the sequencing process. With NGSSets you can perform several operations, such as, import and export FASTQ files, downsampling, remove and add entries, etc.

### Genomic coordinates datasets
A common task in genomics is to work with coordinates of genomic features, commonly represented by BED, GTF, GFF, GFF3 formats. With this API you will be able to do a lot of arithmetic operations with this coordinates, such as, intersect features, get coverage, subtract genome, get flank regions, etc.

### SNPs datasets

The effect of Single Nucleotide Polymorphisms in the genome can be very important, and very different depending on the individual, hence it has been an important area of study. SNP data is commonly stored in a Variant Call File, or VCF file. This API can manage this sort of files and apply a list of filters, such as, quality filter, position filter, heterozygosity filter, etc. Besides, it can predict if the effect of the SNPs is synonymous or nonsynonymous, the amino acid translation, etc.

### Alignment datasets

### How to add NGSSets to my JAVA project?

### Where can I find some examples about its usage?



 
