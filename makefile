

archive:
	rm -f aic-lab1.zip
	zip -r aic-lab1.zip src test webapp build.xml README --exclude \*.pyc


.PHONY: archive
