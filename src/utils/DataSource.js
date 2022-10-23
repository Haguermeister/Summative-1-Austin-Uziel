import React, { useState, useEffect } from 'react';

// Modal from: https://www.linkedin.com/learning/react-design-patterns/take-your-react-skills-to-the-next-level?autoplay=true
export const DataSource = ({ getDataFunc = () =>{}, resourceName, children }) => {
    const [state, setState] = useState(null);

    useEffect(() => {
        (async () => {
            const data = await getDataFunc();
            setState(data)
        })();
    }, [getDataFunc]);

    return (
        <>
            {React.Children.map(children, child => {
                if (React.isValidElement(child)) {
                    return React.cloneElement(child, { [ resourceName]: state});
                }

                return child;
            })}
        </>
    );
}