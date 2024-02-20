import React, { useState } from 'react';
import "../../App.css";

function Slides() {
    const [currentSlideIndex, setCurrentSlideIndex] = useState(0);
    const [slides, setSlides] = useState([
        { title: "Slide 1", text: "Text for Slide 1" },
        { title: "Slide 2", text: "Text for Slide 2" },
        { title: "Slide 3", text: "Text for Slide 3" }
    ]);

    const goToNextSlide = () => {
        if (currentSlideIndex < slides.length - 1) {
            setCurrentSlideIndex(currentSlideIndex + 1);
        }
    };

    const goToPrevSlide = () => {
        if (currentSlideIndex > 0) {
            setCurrentSlideIndex(currentSlideIndex - 1);
        }
    };

    const restartSlides = () => {
        setCurrentSlideIndex(0);
    };

    return (
        <div className="example">
            <div id="navigation" className="text-center">
                <button data-testid="button-restart" className="small outlined" onClick={restartSlides}
                        disabled={currentSlideIndex === 0}>Restart
                </button>
                <button data-testid="" className="small" onClick={goToPrevSlide}
                        disabled={currentSlideIndex === 0}>Prev
                </button>
                <button data-testid="button-next" className="small" onClick={goToNextSlide}
                        disabled={currentSlideIndex === slides.length - 1}>Next
                </button>
            </div>
            <div id="slide" className="text-center">
                <h1 data-testid="title">{slides[currentSlideIndex].title}</h1>
            </div>
        </div>
    );
}

export default Slides;
