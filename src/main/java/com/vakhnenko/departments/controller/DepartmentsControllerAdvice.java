package com.vakhnenko.departments.controller;

//@ControllerAdvice
public class DepartmentsControllerAdvice {
    /*
    private static final Logger logger = LoggerFactory.getLogger(DepartmentsControllerAdvice.class);

    @ExceptionHandler
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        logger.error("8687650044: HTTP Error!!", e);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("500");
        return mav;
    }

    @ExceptionHandler
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException400(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        return "400";
    }

    //@ResponseStatus(HttpStatus.FORBIDDEN)
    //@ExceptionHandler(AccessDeniedException.class)
    public String handleException403(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        return "403";
    }

    //@ResponseStatus(HttpStatus.NOT_FOUND)
    //@ExceptionHandler(NoHandlerFoundException.class)
    public String handleException404(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        return "404";
    }

    //@ExceptionHandler
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException500(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        return "500";
    }*/
}