import React from 'react';
import './Toggler.scss';


const Toggler = () => {
    const handleClick = () => {
        document.getElementById('topmenu').classList.toggle('active');
    };

    return (
        <div className="toggler" onClick={handleClick}>
            <i className="icon icon-toggler"></i>
        </div>
    );
};

export default Toggler;