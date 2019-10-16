import React from 'react';
import {Route, Redirect} from "react-router-dom";
import {connect} from "react-redux";
import PropTyped from "prop-types";

const SecureRoute = ({component: Component, security, ...otherProps}) => (
    <Route {...otherProps}
           render={
               props => security.validToken === true ?
                   (<Component {...props}/>) :
                   (<Redirect to="/login"/>)
           }
    />
);

SecureRoute.propTypes = {
    security: PropTyped.object.isRequired
};

const mapStateToProps = state => ({
    security: state.security
});

export default connect(mapStateToProps)(SecureRoute);