"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var element_1 = require("./element");
// types
/* generated @ 2018-11-12T13:41:14.486 */
var MainTypes;
(function (MainTypes) {
    var AccessibilityLiveRegionEnum;
    (function (AccessibilityLiveRegionEnum) {
        AccessibilityLiveRegionEnum["assertive"] = "assertive";
        AccessibilityLiveRegionEnum["none"] = "none";
        AccessibilityLiveRegionEnum["polite"] = "polite";
    })(AccessibilityLiveRegionEnum = MainTypes.AccessibilityLiveRegionEnum || (MainTypes.AccessibilityLiveRegionEnum = {}));
    var AlignmentModeEnum;
    (function (AlignmentModeEnum) {
        AlignmentModeEnum["alignBounds"] = "alignBounds";
        AlignmentModeEnum["alignMargins"] = "alignMargins";
    })(AlignmentModeEnum = MainTypes.AlignmentModeEnum || (MainTypes.AlignmentModeEnum = {}));
    var AutoLinkFlagsEnum;
    (function (AutoLinkFlagsEnum) {
        AutoLinkFlagsEnum["all"] = "all";
        AutoLinkFlagsEnum["email"] = "email";
        AutoLinkFlagsEnum["map"] = "map";
        AutoLinkFlagsEnum["none"] = "none";
        AutoLinkFlagsEnum["phone"] = "phone";
        AutoLinkFlagsEnum["web"] = "web";
    })(AutoLinkFlagsEnum = MainTypes.AutoLinkFlagsEnum || (MainTypes.AutoLinkFlagsEnum = {}));
    var AutoSizeTextTypeEnum;
    (function (AutoSizeTextTypeEnum) {
        AutoSizeTextTypeEnum["none"] = "none";
        AutoSizeTextTypeEnum["uniform"] = "uniform";
    })(AutoSizeTextTypeEnum = MainTypes.AutoSizeTextTypeEnum || (MainTypes.AutoSizeTextTypeEnum = {}));
    var BackgroundTintModeEnum;
    (function (BackgroundTintModeEnum) {
        BackgroundTintModeEnum["add"] = "add";
        BackgroundTintModeEnum["multiply"] = "multiply";
        BackgroundTintModeEnum["screen"] = "screen";
        BackgroundTintModeEnum["src_atop"] = "src_atop";
        BackgroundTintModeEnum["src_in"] = "src_in";
        BackgroundTintModeEnum["src_over"] = "src_over";
    })(BackgroundTintModeEnum = MainTypes.BackgroundTintModeEnum || (MainTypes.BackgroundTintModeEnum = {}));
    var BreakStrategyEnum;
    (function (BreakStrategyEnum) {
        BreakStrategyEnum["balanced"] = "balanced";
        BreakStrategyEnum["high_quality"] = "high_quality";
        BreakStrategyEnum["simple"] = "simple";
    })(BreakStrategyEnum = MainTypes.BreakStrategyEnum || (MainTypes.BreakStrategyEnum = {}));
    var ButtonTintModeEnum;
    (function (ButtonTintModeEnum) {
        ButtonTintModeEnum["add"] = "add";
        ButtonTintModeEnum["multiply"] = "multiply";
        ButtonTintModeEnum["screen"] = "screen";
        ButtonTintModeEnum["src_atop"] = "src_atop";
        ButtonTintModeEnum["src_in"] = "src_in";
        ButtonTintModeEnum["src_over"] = "src_over";
    })(ButtonTintModeEnum = MainTypes.ButtonTintModeEnum || (MainTypes.ButtonTintModeEnum = {}));
    var CheckMarkTintModeEnum;
    (function (CheckMarkTintModeEnum) {
        CheckMarkTintModeEnum["add"] = "add";
        CheckMarkTintModeEnum["multiply"] = "multiply";
        CheckMarkTintModeEnum["screen"] = "screen";
        CheckMarkTintModeEnum["src_atop"] = "src_atop";
        CheckMarkTintModeEnum["src_in"] = "src_in";
        CheckMarkTintModeEnum["src_over"] = "src_over";
    })(CheckMarkTintModeEnum = MainTypes.CheckMarkTintModeEnum || (MainTypes.CheckMarkTintModeEnum = {}));
    var ChoiceModeEnum;
    (function (ChoiceModeEnum) {
        ChoiceModeEnum["multipleChoice"] = "multipleChoice";
        ChoiceModeEnum["multipleChoiceModal"] = "multipleChoiceModal";
        ChoiceModeEnum["none"] = "none";
        ChoiceModeEnum["singleChoice"] = "singleChoice";
    })(ChoiceModeEnum = MainTypes.ChoiceModeEnum || (MainTypes.ChoiceModeEnum = {}));
    var DrawableTintModeEnum;
    (function (DrawableTintModeEnum) {
        DrawableTintModeEnum["add"] = "add";
        DrawableTintModeEnum["multiply"] = "multiply";
        DrawableTintModeEnum["screen"] = "screen";
        DrawableTintModeEnum["src_atop"] = "src_atop";
        DrawableTintModeEnum["src_in"] = "src_in";
        DrawableTintModeEnum["src_over"] = "src_over";
    })(DrawableTintModeEnum = MainTypes.DrawableTintModeEnum || (MainTypes.DrawableTintModeEnum = {}));
    var DrawingCacheQualityEnum;
    (function (DrawingCacheQualityEnum) {
        DrawingCacheQualityEnum["auto"] = "auto";
        DrawingCacheQualityEnum["high"] = "high";
        DrawingCacheQualityEnum["low"] = "low";
    })(DrawingCacheQualityEnum = MainTypes.DrawingCacheQualityEnum || (MainTypes.DrawingCacheQualityEnum = {}));
    var DropDownHeightEnum;
    (function (DropDownHeightEnum) {
        DropDownHeightEnum["fill_parent"] = "fill_parent";
        DropDownHeightEnum["match_parent"] = "match_parent";
        DropDownHeightEnum["wrap_content"] = "wrap_content";
    })(DropDownHeightEnum = MainTypes.DropDownHeightEnum || (MainTypes.DropDownHeightEnum = {}));
    var DropDownWidthEnum;
    (function (DropDownWidthEnum) {
        DropDownWidthEnum["fill_parent"] = "fill_parent";
        DropDownWidthEnum["match_parent"] = "match_parent";
        DropDownWidthEnum["wrap_content"] = "wrap_content";
    })(DropDownWidthEnum = MainTypes.DropDownWidthEnum || (MainTypes.DropDownWidthEnum = {}));
    var EllipsizeEnum;
    (function (EllipsizeEnum) {
        EllipsizeEnum["end"] = "end";
        EllipsizeEnum["marquee"] = "marquee";
        EllipsizeEnum["middle"] = "middle";
        EllipsizeEnum["none"] = "none";
        EllipsizeEnum["start"] = "start";
    })(EllipsizeEnum = MainTypes.EllipsizeEnum || (MainTypes.EllipsizeEnum = {}));
    var FocusableEnum;
    (function (FocusableEnum) {
        FocusableEnum["auto"] = "auto";
    })(FocusableEnum = MainTypes.FocusableEnum || (MainTypes.FocusableEnum = {}));
    var ForegroundGravityFlagsEnum;
    (function (ForegroundGravityFlagsEnum) {
        ForegroundGravityFlagsEnum["bottom"] = "bottom";
        ForegroundGravityFlagsEnum["center"] = "center";
        ForegroundGravityFlagsEnum["center_horizontal"] = "center_horizontal";
        ForegroundGravityFlagsEnum["center_vertical"] = "center_vertical";
        ForegroundGravityFlagsEnum["clip_horizontal"] = "clip_horizontal";
        ForegroundGravityFlagsEnum["clip_vertical"] = "clip_vertical";
        ForegroundGravityFlagsEnum["fill"] = "fill";
        ForegroundGravityFlagsEnum["fill_horizontal"] = "fill_horizontal";
        ForegroundGravityFlagsEnum["fill_vertical"] = "fill_vertical";
        ForegroundGravityFlagsEnum["left"] = "left";
        ForegroundGravityFlagsEnum["right"] = "right";
        ForegroundGravityFlagsEnum["top"] = "top";
    })(ForegroundGravityFlagsEnum = MainTypes.ForegroundGravityFlagsEnum || (MainTypes.ForegroundGravityFlagsEnum = {}));
    var ForegroundTintModeEnum;
    (function (ForegroundTintModeEnum) {
        ForegroundTintModeEnum["add"] = "add";
        ForegroundTintModeEnum["multiply"] = "multiply";
        ForegroundTintModeEnum["screen"] = "screen";
        ForegroundTintModeEnum["src_atop"] = "src_atop";
        ForegroundTintModeEnum["src_in"] = "src_in";
        ForegroundTintModeEnum["src_over"] = "src_over";
    })(ForegroundTintModeEnum = MainTypes.ForegroundTintModeEnum || (MainTypes.ForegroundTintModeEnum = {}));
    var GravityFlagsEnum;
    (function (GravityFlagsEnum) {
        GravityFlagsEnum["bottom"] = "bottom";
        GravityFlagsEnum["center"] = "center";
        GravityFlagsEnum["center_horizontal"] = "center_horizontal";
        GravityFlagsEnum["center_vertical"] = "center_vertical";
        GravityFlagsEnum["clip_horizontal"] = "clip_horizontal";
        GravityFlagsEnum["clip_vertical"] = "clip_vertical";
        GravityFlagsEnum["end"] = "end";
        GravityFlagsEnum["fill"] = "fill";
        GravityFlagsEnum["fill_horizontal"] = "fill_horizontal";
        GravityFlagsEnum["fill_vertical"] = "fill_vertical";
        GravityFlagsEnum["left"] = "left";
        GravityFlagsEnum["right"] = "right";
        GravityFlagsEnum["start"] = "start";
        GravityFlagsEnum["top"] = "top";
    })(GravityFlagsEnum = MainTypes.GravityFlagsEnum || (MainTypes.GravityFlagsEnum = {}));
    var GravityFlagsEnum_;
    (function (GravityFlagsEnum_) {
        GravityFlagsEnum_["bottom"] = "bottom";
        GravityFlagsEnum_["center"] = "center";
        GravityFlagsEnum_["center_horizontal"] = "center_horizontal";
        GravityFlagsEnum_["center_vertical"] = "center_vertical";
        GravityFlagsEnum_["clip_horizontal"] = "clip_horizontal";
        GravityFlagsEnum_["clip_vertical"] = "clip_vertical";
        GravityFlagsEnum_["end"] = "end";
        GravityFlagsEnum_["fill"] = "fill";
        GravityFlagsEnum_["fill_horizontal"] = "fill_horizontal";
        GravityFlagsEnum_["fill_vertical"] = "fill_vertical";
        GravityFlagsEnum_["left"] = "left";
        GravityFlagsEnum_["right"] = "right";
        GravityFlagsEnum_["start"] = "start";
        GravityFlagsEnum_["top"] = "top";
    })(GravityFlagsEnum_ = MainTypes.GravityFlagsEnum_ || (MainTypes.GravityFlagsEnum_ = {}));
    var GravityFlagsEnum__;
    (function (GravityFlagsEnum__) {
        GravityFlagsEnum__["bottom"] = "bottom";
        GravityFlagsEnum__["center"] = "center";
        GravityFlagsEnum__["center_horizontal"] = "center_horizontal";
        GravityFlagsEnum__["center_vertical"] = "center_vertical";
        GravityFlagsEnum__["clip_horizontal"] = "clip_horizontal";
        GravityFlagsEnum__["clip_vertical"] = "clip_vertical";
        GravityFlagsEnum__["end"] = "end";
        GravityFlagsEnum__["fill"] = "fill";
        GravityFlagsEnum__["fill_horizontal"] = "fill_horizontal";
        GravityFlagsEnum__["fill_vertical"] = "fill_vertical";
        GravityFlagsEnum__["left"] = "left";
        GravityFlagsEnum__["right"] = "right";
        GravityFlagsEnum__["start"] = "start";
        GravityFlagsEnum__["top"] = "top";
    })(GravityFlagsEnum__ = MainTypes.GravityFlagsEnum__ || (MainTypes.GravityFlagsEnum__ = {}));
    var GravityFlagsEnum___;
    (function (GravityFlagsEnum___) {
        GravityFlagsEnum___["bottom"] = "bottom";
        GravityFlagsEnum___["center"] = "center";
        GravityFlagsEnum___["center_horizontal"] = "center_horizontal";
        GravityFlagsEnum___["center_vertical"] = "center_vertical";
        GravityFlagsEnum___["clip_horizontal"] = "clip_horizontal";
        GravityFlagsEnum___["clip_vertical"] = "clip_vertical";
        GravityFlagsEnum___["end"] = "end";
        GravityFlagsEnum___["fill"] = "fill";
        GravityFlagsEnum___["fill_horizontal"] = "fill_horizontal";
        GravityFlagsEnum___["fill_vertical"] = "fill_vertical";
        GravityFlagsEnum___["left"] = "left";
        GravityFlagsEnum___["right"] = "right";
        GravityFlagsEnum___["start"] = "start";
        GravityFlagsEnum___["top"] = "top";
    })(GravityFlagsEnum___ = MainTypes.GravityFlagsEnum___ || (MainTypes.GravityFlagsEnum___ = {}));
    var GravityFlagsEnum____;
    (function (GravityFlagsEnum____) {
        GravityFlagsEnum____["bottom"] = "bottom";
        GravityFlagsEnum____["center"] = "center";
        GravityFlagsEnum____["center_horizontal"] = "center_horizontal";
        GravityFlagsEnum____["center_vertical"] = "center_vertical";
        GravityFlagsEnum____["clip_horizontal"] = "clip_horizontal";
        GravityFlagsEnum____["clip_vertical"] = "clip_vertical";
        GravityFlagsEnum____["end"] = "end";
        GravityFlagsEnum____["fill"] = "fill";
        GravityFlagsEnum____["fill_horizontal"] = "fill_horizontal";
        GravityFlagsEnum____["fill_vertical"] = "fill_vertical";
        GravityFlagsEnum____["left"] = "left";
        GravityFlagsEnum____["right"] = "right";
        GravityFlagsEnum____["start"] = "start";
        GravityFlagsEnum____["top"] = "top";
    })(GravityFlagsEnum____ = MainTypes.GravityFlagsEnum____ || (MainTypes.GravityFlagsEnum____ = {}));
    var HyphenationFrequencyEnum;
    (function (HyphenationFrequencyEnum) {
        HyphenationFrequencyEnum["full"] = "full";
        HyphenationFrequencyEnum["none"] = "none";
        HyphenationFrequencyEnum["normal"] = "normal";
    })(HyphenationFrequencyEnum = MainTypes.HyphenationFrequencyEnum || (MainTypes.HyphenationFrequencyEnum = {}));
    var ImeOptionsFlagsEnum;
    (function (ImeOptionsFlagsEnum) {
        ImeOptionsFlagsEnum["actionDone"] = "actionDone";
        ImeOptionsFlagsEnum["actionGo"] = "actionGo";
        ImeOptionsFlagsEnum["actionNext"] = "actionNext";
        ImeOptionsFlagsEnum["actionNone"] = "actionNone";
        ImeOptionsFlagsEnum["actionPrevious"] = "actionPrevious";
        ImeOptionsFlagsEnum["actionSearch"] = "actionSearch";
        ImeOptionsFlagsEnum["actionSend"] = "actionSend";
        ImeOptionsFlagsEnum["actionUnspecified"] = "actionUnspecified";
        ImeOptionsFlagsEnum["flagNavigateNext"] = "flagNavigateNext";
        ImeOptionsFlagsEnum["flagNavigatePrevious"] = "flagNavigatePrevious";
        ImeOptionsFlagsEnum["flagNoAccessoryAction"] = "flagNoAccessoryAction";
        ImeOptionsFlagsEnum["flagNoEnterAction"] = "flagNoEnterAction";
        ImeOptionsFlagsEnum["flagNoExtractUi"] = "flagNoExtractUi";
        ImeOptionsFlagsEnum["flagNoFullscreen"] = "flagNoFullscreen";
        ImeOptionsFlagsEnum["flagNoPersonalizedLearning"] = "flagNoPersonalizedLearning";
        ImeOptionsFlagsEnum["normal"] = "normal";
    })(ImeOptionsFlagsEnum = MainTypes.ImeOptionsFlagsEnum || (MainTypes.ImeOptionsFlagsEnum = {}));
    var ImportantForAccessibilityEnum;
    (function (ImportantForAccessibilityEnum) {
        ImportantForAccessibilityEnum["auto"] = "auto";
        ImportantForAccessibilityEnum["no"] = "no";
        ImportantForAccessibilityEnum["noHideDescendants"] = "noHideDescendants";
        ImportantForAccessibilityEnum["yes"] = "yes";
    })(ImportantForAccessibilityEnum = MainTypes.ImportantForAccessibilityEnum || (MainTypes.ImportantForAccessibilityEnum = {}));
    var ImportantForAutofillFlagsEnum;
    (function (ImportantForAutofillFlagsEnum) {
        ImportantForAutofillFlagsEnum["auto"] = "auto";
        ImportantForAutofillFlagsEnum["no"] = "no";
        ImportantForAutofillFlagsEnum["noExcludeDescendants"] = "noExcludeDescendants";
        ImportantForAutofillFlagsEnum["yes"] = "yes";
        ImportantForAutofillFlagsEnum["yesExcludeDescendants"] = "yesExcludeDescendants";
    })(ImportantForAutofillFlagsEnum = MainTypes.ImportantForAutofillFlagsEnum || (MainTypes.ImportantForAutofillFlagsEnum = {}));
    var IndeterminateTintModeEnum;
    (function (IndeterminateTintModeEnum) {
        IndeterminateTintModeEnum["add"] = "add";
        IndeterminateTintModeEnum["multiply"] = "multiply";
        IndeterminateTintModeEnum["screen"] = "screen";
        IndeterminateTintModeEnum["src_atop"] = "src_atop";
        IndeterminateTintModeEnum["src_in"] = "src_in";
        IndeterminateTintModeEnum["src_over"] = "src_over";
    })(IndeterminateTintModeEnum = MainTypes.IndeterminateTintModeEnum || (MainTypes.IndeterminateTintModeEnum = {}));
    var InputTypeFlagsEnum;
    (function (InputTypeFlagsEnum) {
        InputTypeFlagsEnum["date"] = "date";
        InputTypeFlagsEnum["datetime"] = "datetime";
        InputTypeFlagsEnum["none"] = "none";
        InputTypeFlagsEnum["number"] = "number";
        InputTypeFlagsEnum["numberDecimal"] = "numberDecimal";
        InputTypeFlagsEnum["numberPassword"] = "numberPassword";
        InputTypeFlagsEnum["numberSigned"] = "numberSigned";
        InputTypeFlagsEnum["phone"] = "phone";
        InputTypeFlagsEnum["text"] = "text";
        InputTypeFlagsEnum["textAutoComplete"] = "textAutoComplete";
        InputTypeFlagsEnum["textAutoCorrect"] = "textAutoCorrect";
        InputTypeFlagsEnum["textCapCharacters"] = "textCapCharacters";
        InputTypeFlagsEnum["textCapSentences"] = "textCapSentences";
        InputTypeFlagsEnum["textCapWords"] = "textCapWords";
        InputTypeFlagsEnum["textEmailAddress"] = "textEmailAddress";
        InputTypeFlagsEnum["textEmailSubject"] = "textEmailSubject";
        InputTypeFlagsEnum["textFilter"] = "textFilter";
        InputTypeFlagsEnum["textImeMultiLine"] = "textImeMultiLine";
        InputTypeFlagsEnum["textLongMessage"] = "textLongMessage";
        InputTypeFlagsEnum["textMultiLine"] = "textMultiLine";
        InputTypeFlagsEnum["textNoSuggestions"] = "textNoSuggestions";
        InputTypeFlagsEnum["textPassword"] = "textPassword";
        InputTypeFlagsEnum["textPersonName"] = "textPersonName";
        InputTypeFlagsEnum["textPhonetic"] = "textPhonetic";
        InputTypeFlagsEnum["textPostalAddress"] = "textPostalAddress";
        InputTypeFlagsEnum["textShortMessage"] = "textShortMessage";
        InputTypeFlagsEnum["textUri"] = "textUri";
        InputTypeFlagsEnum["textVisiblePassword"] = "textVisiblePassword";
        InputTypeFlagsEnum["textWebEditText"] = "textWebEditText";
        InputTypeFlagsEnum["textWebEmailAddress"] = "textWebEmailAddress";
        InputTypeFlagsEnum["textWebPassword"] = "textWebPassword";
        InputTypeFlagsEnum["time"] = "time";
    })(InputTypeFlagsEnum = MainTypes.InputTypeFlagsEnum || (MainTypes.InputTypeFlagsEnum = {}));
    var JustificationModeEnum;
    (function (JustificationModeEnum) {
        JustificationModeEnum["inter_word"] = "inter_word";
        JustificationModeEnum["none"] = "none";
    })(JustificationModeEnum = MainTypes.JustificationModeEnum || (MainTypes.JustificationModeEnum = {}));
    var LayerTypeEnum;
    (function (LayerTypeEnum) {
        LayerTypeEnum["hardware"] = "hardware";
        LayerTypeEnum["none"] = "none";
        LayerTypeEnum["software"] = "software";
    })(LayerTypeEnum = MainTypes.LayerTypeEnum || (MainTypes.LayerTypeEnum = {}));
    var LayoutDirectionEnum;
    (function (LayoutDirectionEnum) {
        LayoutDirectionEnum["inherit"] = "inherit";
        LayoutDirectionEnum["locale"] = "locale";
        LayoutDirectionEnum["ltr"] = "ltr";
        LayoutDirectionEnum["rtl"] = "rtl";
    })(LayoutDirectionEnum = MainTypes.LayoutDirectionEnum || (MainTypes.LayoutDirectionEnum = {}));
    var LayoutGravityFlagsEnum;
    (function (LayoutGravityFlagsEnum) {
        LayoutGravityFlagsEnum["bottom"] = "bottom";
        LayoutGravityFlagsEnum["center"] = "center";
        LayoutGravityFlagsEnum["center_horizontal"] = "center_horizontal";
        LayoutGravityFlagsEnum["center_vertical"] = "center_vertical";
        LayoutGravityFlagsEnum["clip_horizontal"] = "clip_horizontal";
        LayoutGravityFlagsEnum["clip_vertical"] = "clip_vertical";
        LayoutGravityFlagsEnum["end"] = "end";
        LayoutGravityFlagsEnum["fill"] = "fill";
        LayoutGravityFlagsEnum["fill_horizontal"] = "fill_horizontal";
        LayoutGravityFlagsEnum["fill_vertical"] = "fill_vertical";
        LayoutGravityFlagsEnum["left"] = "left";
        LayoutGravityFlagsEnum["right"] = "right";
        LayoutGravityFlagsEnum["start"] = "start";
        LayoutGravityFlagsEnum["top"] = "top";
    })(LayoutGravityFlagsEnum = MainTypes.LayoutGravityFlagsEnum || (MainTypes.LayoutGravityFlagsEnum = {}));
    var LayoutGravityFlagsEnum_;
    (function (LayoutGravityFlagsEnum_) {
        LayoutGravityFlagsEnum_["bottom"] = "bottom";
        LayoutGravityFlagsEnum_["center"] = "center";
        LayoutGravityFlagsEnum_["center_horizontal"] = "center_horizontal";
        LayoutGravityFlagsEnum_["center_vertical"] = "center_vertical";
        LayoutGravityFlagsEnum_["clip_horizontal"] = "clip_horizontal";
        LayoutGravityFlagsEnum_["clip_vertical"] = "clip_vertical";
        LayoutGravityFlagsEnum_["end"] = "end";
        LayoutGravityFlagsEnum_["fill"] = "fill";
        LayoutGravityFlagsEnum_["fill_horizontal"] = "fill_horizontal";
        LayoutGravityFlagsEnum_["fill_vertical"] = "fill_vertical";
        LayoutGravityFlagsEnum_["left"] = "left";
        LayoutGravityFlagsEnum_["right"] = "right";
        LayoutGravityFlagsEnum_["start"] = "start";
        LayoutGravityFlagsEnum_["top"] = "top";
    })(LayoutGravityFlagsEnum_ = MainTypes.LayoutGravityFlagsEnum_ || (MainTypes.LayoutGravityFlagsEnum_ = {}));
    var LayoutHeightEnum;
    (function (LayoutHeightEnum) {
        LayoutHeightEnum["fill_parent"] = "fill_parent";
        LayoutHeightEnum["match_parent"] = "match_parent";
        LayoutHeightEnum["wrap_content"] = "wrap_content";
    })(LayoutHeightEnum = MainTypes.LayoutHeightEnum || (MainTypes.LayoutHeightEnum = {}));
    var LayoutModeEnum;
    (function (LayoutModeEnum) {
        LayoutModeEnum["clipBounds"] = "clipBounds";
        LayoutModeEnum["opticalBounds"] = "opticalBounds";
    })(LayoutModeEnum = MainTypes.LayoutModeEnum || (MainTypes.LayoutModeEnum = {}));
    var LayoutWidthEnum;
    (function (LayoutWidthEnum) {
        LayoutWidthEnum["fill_parent"] = "fill_parent";
        LayoutWidthEnum["match_parent"] = "match_parent";
        LayoutWidthEnum["wrap_content"] = "wrap_content";
    })(LayoutWidthEnum = MainTypes.LayoutWidthEnum || (MainTypes.LayoutWidthEnum = {}));
    var MarqueeRepeatLimitEnum;
    (function (MarqueeRepeatLimitEnum) {
        MarqueeRepeatLimitEnum["marquee_forever"] = "marquee_forever";
    })(MarqueeRepeatLimitEnum = MainTypes.MarqueeRepeatLimitEnum || (MainTypes.MarqueeRepeatLimitEnum = {}));
    var NumColumnsEnum;
    (function (NumColumnsEnum) {
        NumColumnsEnum["auto_fit"] = "auto_fit";
    })(NumColumnsEnum = MainTypes.NumColumnsEnum || (MainTypes.NumColumnsEnum = {}));
    var OrientationEnum;
    (function (OrientationEnum) {
        OrientationEnum["horizontal"] = "horizontal";
        OrientationEnum["vertical"] = "vertical";
    })(OrientationEnum = MainTypes.OrientationEnum || (MainTypes.OrientationEnum = {}));
    var OrientationEnum_;
    (function (OrientationEnum_) {
        OrientationEnum_["horizontal"] = "horizontal";
        OrientationEnum_["vertical"] = "vertical";
    })(OrientationEnum_ = MainTypes.OrientationEnum_ || (MainTypes.OrientationEnum_ = {}));
    var OverScrollModeEnum;
    (function (OverScrollModeEnum) {
        OverScrollModeEnum["always"] = "always";
        OverScrollModeEnum["ifContentScrolls"] = "ifContentScrolls";
        OverScrollModeEnum["never"] = "never";
    })(OverScrollModeEnum = MainTypes.OverScrollModeEnum || (MainTypes.OverScrollModeEnum = {}));
    var PersistentDrawingCacheFlagsEnum;
    (function (PersistentDrawingCacheFlagsEnum) {
        PersistentDrawingCacheFlagsEnum["all"] = "all";
        PersistentDrawingCacheFlagsEnum["animation"] = "animation";
        PersistentDrawingCacheFlagsEnum["none"] = "none";
        PersistentDrawingCacheFlagsEnum["scrolling"] = "scrolling";
    })(PersistentDrawingCacheFlagsEnum = MainTypes.PersistentDrawingCacheFlagsEnum || (MainTypes.PersistentDrawingCacheFlagsEnum = {}));
    var ProgressBackgroundTintModeEnum;
    (function (ProgressBackgroundTintModeEnum) {
        ProgressBackgroundTintModeEnum["add"] = "add";
        ProgressBackgroundTintModeEnum["multiply"] = "multiply";
        ProgressBackgroundTintModeEnum["screen"] = "screen";
        ProgressBackgroundTintModeEnum["src_atop"] = "src_atop";
        ProgressBackgroundTintModeEnum["src_in"] = "src_in";
        ProgressBackgroundTintModeEnum["src_over"] = "src_over";
    })(ProgressBackgroundTintModeEnum = MainTypes.ProgressBackgroundTintModeEnum || (MainTypes.ProgressBackgroundTintModeEnum = {}));
    var ProgressTintModeEnum;
    (function (ProgressTintModeEnum) {
        ProgressTintModeEnum["add"] = "add";
        ProgressTintModeEnum["multiply"] = "multiply";
        ProgressTintModeEnum["screen"] = "screen";
        ProgressTintModeEnum["src_atop"] = "src_atop";
        ProgressTintModeEnum["src_in"] = "src_in";
        ProgressTintModeEnum["src_over"] = "src_over";
    })(ProgressTintModeEnum = MainTypes.ProgressTintModeEnum || (MainTypes.ProgressTintModeEnum = {}));
    var ScaleTypeEnum;
    (function (ScaleTypeEnum) {
        ScaleTypeEnum["center"] = "center";
        ScaleTypeEnum["centerCrop"] = "centerCrop";
        ScaleTypeEnum["centerInside"] = "centerInside";
        ScaleTypeEnum["fitCenter"] = "fitCenter";
        ScaleTypeEnum["fitEnd"] = "fitEnd";
        ScaleTypeEnum["fitStart"] = "fitStart";
        ScaleTypeEnum["fitXY"] = "fitXY";
        ScaleTypeEnum["matrix"] = "matrix";
    })(ScaleTypeEnum = MainTypes.ScaleTypeEnum || (MainTypes.ScaleTypeEnum = {}));
    var ScrollIndicatorsFlagsEnum;
    (function (ScrollIndicatorsFlagsEnum) {
        ScrollIndicatorsFlagsEnum["bottom"] = "bottom";
        ScrollIndicatorsFlagsEnum["end"] = "end";
        ScrollIndicatorsFlagsEnum["left"] = "left";
        ScrollIndicatorsFlagsEnum["none"] = "none";
        ScrollIndicatorsFlagsEnum["right"] = "right";
        ScrollIndicatorsFlagsEnum["start"] = "start";
        ScrollIndicatorsFlagsEnum["top"] = "top";
    })(ScrollIndicatorsFlagsEnum = MainTypes.ScrollIndicatorsFlagsEnum || (MainTypes.ScrollIndicatorsFlagsEnum = {}));
    var ScrollbarStyleEnum;
    (function (ScrollbarStyleEnum) {
        ScrollbarStyleEnum["insideInset"] = "insideInset";
        ScrollbarStyleEnum["insideOverlay"] = "insideOverlay";
        ScrollbarStyleEnum["outsideInset"] = "outsideInset";
        ScrollbarStyleEnum["outsideOverlay"] = "outsideOverlay";
    })(ScrollbarStyleEnum = MainTypes.ScrollbarStyleEnum || (MainTypes.ScrollbarStyleEnum = {}));
    var SecondaryProgressTintModeEnum;
    (function (SecondaryProgressTintModeEnum) {
        SecondaryProgressTintModeEnum["add"] = "add";
        SecondaryProgressTintModeEnum["multiply"] = "multiply";
        SecondaryProgressTintModeEnum["screen"] = "screen";
        SecondaryProgressTintModeEnum["src_atop"] = "src_atop";
        SecondaryProgressTintModeEnum["src_in"] = "src_in";
        SecondaryProgressTintModeEnum["src_over"] = "src_over";
    })(SecondaryProgressTintModeEnum = MainTypes.SecondaryProgressTintModeEnum || (MainTypes.SecondaryProgressTintModeEnum = {}));
    var ShowDividersFlagsEnum;
    (function (ShowDividersFlagsEnum) {
        ShowDividersFlagsEnum["beginning"] = "beginning";
        ShowDividersFlagsEnum["end"] = "end";
        ShowDividersFlagsEnum["middle"] = "middle";
        ShowDividersFlagsEnum["none"] = "none";
    })(ShowDividersFlagsEnum = MainTypes.ShowDividersFlagsEnum || (MainTypes.ShowDividersFlagsEnum = {}));
    var StretchModeEnum;
    (function (StretchModeEnum) {
        StretchModeEnum["columnWidth"] = "columnWidth";
        StretchModeEnum["none"] = "none";
        StretchModeEnum["spacingWidth"] = "spacingWidth";
        StretchModeEnum["spacingWidthUniform"] = "spacingWidthUniform";
    })(StretchModeEnum = MainTypes.StretchModeEnum || (MainTypes.StretchModeEnum = {}));
    var TextAlignmentEnum;
    (function (TextAlignmentEnum) {
        TextAlignmentEnum["center"] = "center";
        TextAlignmentEnum["gravity"] = "gravity";
        TextAlignmentEnum["inherit"] = "inherit";
        TextAlignmentEnum["textEnd"] = "textEnd";
        TextAlignmentEnum["textStart"] = "textStart";
        TextAlignmentEnum["viewEnd"] = "viewEnd";
        TextAlignmentEnum["viewStart"] = "viewStart";
    })(TextAlignmentEnum = MainTypes.TextAlignmentEnum || (MainTypes.TextAlignmentEnum = {}));
    var TextDirectionEnum;
    (function (TextDirectionEnum) {
        TextDirectionEnum["anyRtl"] = "anyRtl";
        TextDirectionEnum["firstStrong"] = "firstStrong";
        TextDirectionEnum["firstStrongLtr"] = "firstStrongLtr";
        TextDirectionEnum["firstStrongRtl"] = "firstStrongRtl";
        TextDirectionEnum["inherit"] = "inherit";
        TextDirectionEnum["locale"] = "locale";
        TextDirectionEnum["ltr"] = "ltr";
        TextDirectionEnum["rtl"] = "rtl";
    })(TextDirectionEnum = MainTypes.TextDirectionEnum || (MainTypes.TextDirectionEnum = {}));
    var ThumbTintModeEnum;
    (function (ThumbTintModeEnum) {
        ThumbTintModeEnum["add"] = "add";
        ThumbTintModeEnum["multiply"] = "multiply";
        ThumbTintModeEnum["screen"] = "screen";
        ThumbTintModeEnum["src_atop"] = "src_atop";
        ThumbTintModeEnum["src_in"] = "src_in";
        ThumbTintModeEnum["src_over"] = "src_over";
    })(ThumbTintModeEnum = MainTypes.ThumbTintModeEnum || (MainTypes.ThumbTintModeEnum = {}));
    var TickMarkTintModeEnum;
    (function (TickMarkTintModeEnum) {
        TickMarkTintModeEnum["add"] = "add";
        TickMarkTintModeEnum["multiply"] = "multiply";
        TickMarkTintModeEnum["screen"] = "screen";
        TickMarkTintModeEnum["src_atop"] = "src_atop";
        TickMarkTintModeEnum["src_in"] = "src_in";
        TickMarkTintModeEnum["src_over"] = "src_over";
    })(TickMarkTintModeEnum = MainTypes.TickMarkTintModeEnum || (MainTypes.TickMarkTintModeEnum = {}));
    var TrackTintModeEnum;
    (function (TrackTintModeEnum) {
        TrackTintModeEnum["add"] = "add";
        TrackTintModeEnum["multiply"] = "multiply";
        TrackTintModeEnum["screen"] = "screen";
        TrackTintModeEnum["src_atop"] = "src_atop";
        TrackTintModeEnum["src_in"] = "src_in";
        TrackTintModeEnum["src_over"] = "src_over";
    })(TrackTintModeEnum = MainTypes.TrackTintModeEnum || (MainTypes.TrackTintModeEnum = {}));
    var TranscriptModeEnum;
    (function (TranscriptModeEnum) {
        TranscriptModeEnum["alwaysScroll"] = "alwaysScroll";
        TranscriptModeEnum["disabled"] = "disabled";
        TranscriptModeEnum["normal"] = "normal";
    })(TranscriptModeEnum = MainTypes.TranscriptModeEnum || (MainTypes.TranscriptModeEnum = {}));
    var VerticalScrollbarPositionEnum;
    (function (VerticalScrollbarPositionEnum) {
        VerticalScrollbarPositionEnum["defaultPosition"] = "defaultPosition";
        VerticalScrollbarPositionEnum["left"] = "left";
        VerticalScrollbarPositionEnum["right"] = "right";
    })(VerticalScrollbarPositionEnum = MainTypes.VerticalScrollbarPositionEnum || (MainTypes.VerticalScrollbarPositionEnum = {}));
    var VisibilityEnum;
    (function (VisibilityEnum) {
        VisibilityEnum["gone"] = "gone";
        VisibilityEnum["invisible"] = "invisible";
        VisibilityEnum["visible"] = "visible";
    })(VisibilityEnum = MainTypes.VisibilityEnum || (MainTypes.VisibilityEnum = {}));
})(MainTypes = exports.MainTypes || (exports.MainTypes = {}));
// elements
exports.AdapterViewAnimator = function (attributes, children) {
    return element_1.element('adapterViewAnimator', attributes, children);
};
exports.FrameLayout = function (attributes, children) {
    return element_1.element('frameLayout', attributes, children);
};
exports.View = function (attributes, children) {
    return element_1.element('view', attributes, children);
};
exports.SearchView = function (attributes, children) {
    return element_1.element('searchView', attributes, children);
};
exports.TableRow = function (attributes, children) {
    return element_1.element('tableRow', attributes, children);
};
exports.RelativeLayout = function (attributes, children) {
    return element_1.element('relativeLayout', attributes, children);
};
exports.ImageSwitcher = function (attributes, children) {
    return element_1.element('imageSwitcher', attributes, children);
};
exports.RadioGroup = function (attributes, children) {
    return element_1.element('radioGroup', attributes, children);
};
exports.Toolbar = function (attributes, children) {
    return element_1.element('toolbar', attributes, children);
};
exports.ListView = function (attributes, children) {
    return element_1.element('listView', attributes, children);
};
exports.Spinner = function (attributes, children) {
    return element_1.element('spinner', attributes, children);
};
exports.ScrollView = function (attributes, children) {
    return element_1.element('scrollView', attributes, children);
};
exports.ViewFlipper = function (attributes, children) {
    return element_1.element('viewFlipper', attributes, children);
};
exports.ViewSwitcher = function (attributes, children) {
    return element_1.element('viewSwitcher', attributes, children);
};
exports.DatePicker = function (attributes, children) {
    return element_1.element('datePicker', attributes, children);
};
exports.StackView = function (attributes, children) {
    return element_1.element('stackView', attributes, children);
};
exports.TimePicker = function (attributes, children) {
    return element_1.element('timePicker', attributes, children);
};
exports.MediaController = function (attributes, children) {
    return element_1.element('mediaController', attributes, children);
};
exports.ActionMenuView = function (attributes, children) {
    return element_1.element('actionMenuView', attributes, children);
};
exports.ZoomControls = function (attributes, children) {
    return element_1.element('zoomControls', attributes, children);
};
exports.AdapterView = function (attributes, children) {
    return element_1.element('adapterView', attributes, children);
};
exports.ViewAnimator = function (attributes, children) {
    return element_1.element('viewAnimator', attributes, children);
};
exports.HorizontalScrollView = function (attributes, children) {
    return element_1.element('horizontalScrollView', attributes, children);
};
exports.CalendarView = function (attributes, children) {
    return element_1.element('calendarView', attributes, children);
};
exports.AbsListView = function (attributes, children) {
    return element_1.element('absListView', attributes, children);
};
exports.LinearLayout = function (attributes, children) {
    return element_1.element('linearLayout', attributes, children);
};
exports.NumberPicker = function (attributes, children) {
    return element_1.element('numberPicker', attributes, children);
};
exports.AdapterViewFlipper = function (attributes, children) {
    return element_1.element('adapterViewFlipper', attributes, children);
};
exports.AbsSpinner = function (attributes, children) {
    return element_1.element('absSpinner', attributes, children);
};
exports.ViewGroup = function (attributes, children) {
    return element_1.element('viewGroup', attributes, children);
};
exports.TextSwitcher = function (attributes, children) {
    return element_1.element('textSwitcher', attributes, children);
};
exports.ExpandableListView = function (attributes, children) {
    return element_1.element('expandableListView', attributes, children);
};
exports.GridView = function (attributes, children) {
    return element_1.element('gridView', attributes, children);
};
exports.TableLayout = function (attributes, children) {
    return element_1.element('tableLayout', attributes, children);
};
exports.TabHost = function (attributes, children) {
    return element_1.element('tabHost', attributes, children);
};
exports.GridLayout = function (attributes, children) {
    return element_1.element('gridLayout', attributes, children);
};
exports.TabWidget = function (attributes, children) {
    return element_1.element('tabWidget', attributes, children);
};
exports.Space = function (attributes, children) {
    return element_1.element('space', attributes, children);
};
exports.MultiAutoCompleteTextView = function (attributes, children) {
    return element_1.element('multiAutoCompleteTextView', attributes, children);
};
exports.QuickContactBadge = function (attributes, children) {
    return element_1.element('quickContactBadge', attributes, children);
};
exports.EditText = function (attributes, children) {
    return element_1.element('editText', attributes, children);
};
exports.SurfaceView = function (attributes, children) {
    return element_1.element('surfaceView', attributes, children);
};
exports.RatingBar = function (attributes, children) {
    return element_1.element('ratingBar', attributes, children);
};
exports.ImageView = function (attributes, children) {
    return element_1.element('imageView', attributes, children);
};
exports.AbsSeekBar = function (attributes, children) {
    return element_1.element('absSeekBar', attributes, children);
};
exports.ViewStub = function (attributes, children) {
    return element_1.element('viewStub', attributes, children);
};
exports.CheckedTextView = function (attributes, children) {
    return element_1.element('checkedTextView', attributes, children);
};
exports.TextureView = function (attributes, children) {
    return element_1.element('textureView', attributes, children);
};
exports.ProgressBar = function (attributes, children) {
    return element_1.element('progressBar', attributes, children);
};
exports.TextView = function (attributes, children) {
    return element_1.element('textView', attributes, children);
};
exports.CheckBox = function (attributes, children) {
    return element_1.element('checkBox', attributes, children);
};
exports.Switch = function (attributes, children) {
    return element_1.element('switch', attributes, children);
};
exports.RadioButton = function (attributes, children) {
    return element_1.element('radioButton', attributes, children);
};
exports.SeekBar = function (attributes, children) {
    return element_1.element('seekBar', attributes, children);
};
exports.CompoundButton = function (attributes, children) {
    return element_1.element('compoundButton', attributes, children);
};
exports.ToggleButton = function (attributes, children) {
    return element_1.element('toggleButton', attributes, children);
};
exports.TextClock = function (attributes, children) {
    return element_1.element('textClock', attributes, children);
};
exports.ImageButton = function (attributes, children) {
    return element_1.element('imageButton', attributes, children);
};
exports.Chronometer = function (attributes, children) {
    return element_1.element('chronometer', attributes, children);
};
exports.VideoView = function (attributes, children) {
    return element_1.element('videoView', attributes, children);
};
exports.AutoCompleteTextView = function (attributes, children) {
    return element_1.element('autoCompleteTextView', attributes, children);
};
exports.Button = function (attributes, children) {
    return element_1.element('button', attributes, children);
};
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJmaWxlIjoiL1VzZXJzL2RlcmsvanNQcm9qZWN0cy9zb3VyY2VyZXIvanMvbWFpbi50cyIsIm1hcHBpbmdzIjoiOztBQUFBLHFDQUFpRDtBQUVqRCxRQUFRO0FBQ1IseUNBQXlDO0FBQ3pDLElBQWlCLFNBQVMsQ0E2OEJ6QjtBQTc4QkQsV0FBaUIsU0FBUztJQWlDeEIsSUFBWSwyQkFJWDtJQUpELFdBQVksMkJBQTJCO1FBQ3JDLHNEQUF1QixDQUFBO1FBQ3ZCLDRDQUFhLENBQUE7UUFDYixnREFBaUIsQ0FBQTtJQUNuQixDQUFDLEVBSlcsMkJBQTJCLEdBQTNCLHFDQUEyQixLQUEzQixxQ0FBMkIsUUFJdEM7SUFnQkQsSUFBWSxpQkFHWDtJQUhELFdBQVksaUJBQWlCO1FBQzNCLGdEQUEyQixDQUFBO1FBQzNCLGtEQUE2QixDQUFBO0lBQy9CLENBQUMsRUFIVyxpQkFBaUIsR0FBakIsMkJBQWlCLEtBQWpCLDJCQUFpQixRQUc1QjtJQVVELElBQVksaUJBT1g7SUFQRCxXQUFZLGlCQUFpQjtRQUMzQixnQ0FBVyxDQUFBO1FBQ1gsb0NBQWUsQ0FBQTtRQUNmLGdDQUFXLENBQUE7UUFDWCxrQ0FBYSxDQUFBO1FBQ2Isb0NBQWUsQ0FBQTtRQUNmLGdDQUFXLENBQUE7SUFDYixDQUFDLEVBUFcsaUJBQWlCLEdBQWpCLDJCQUFpQixLQUFqQiwyQkFBaUIsUUFPNUI7SUFDRCxJQUFZLG9CQUdYO0lBSEQsV0FBWSxvQkFBb0I7UUFDOUIscUNBQWEsQ0FBQTtRQUNiLDJDQUFtQixDQUFBO0lBQ3JCLENBQUMsRUFIVyxvQkFBb0IsR0FBcEIsOEJBQW9CLEtBQXBCLDhCQUFvQixRQUcvQjtJQUNELElBQVksc0JBT1g7SUFQRCxXQUFZLHNCQUFzQjtRQUNoQyxxQ0FBVyxDQUFBO1FBQ1gsK0NBQXFCLENBQUE7UUFDckIsMkNBQWlCLENBQUE7UUFDakIsK0NBQXFCLENBQUE7UUFDckIsMkNBQWlCLENBQUE7UUFDakIsK0NBQXFCLENBQUE7SUFDdkIsQ0FBQyxFQVBXLHNCQUFzQixHQUF0QixnQ0FBc0IsS0FBdEIsZ0NBQXNCLFFBT2pDO0lBQ0QsSUFBWSxpQkFJWDtJQUpELFdBQVksaUJBQWlCO1FBQzNCLDBDQUFxQixDQUFBO1FBQ3JCLGtEQUE2QixDQUFBO1FBQzdCLHNDQUFpQixDQUFBO0lBQ25CLENBQUMsRUFKVyxpQkFBaUIsR0FBakIsMkJBQWlCLEtBQWpCLDJCQUFpQixRQUk1QjtJQUdELElBQVksa0JBT1g7SUFQRCxXQUFZLGtCQUFrQjtRQUM1QixpQ0FBVyxDQUFBO1FBQ1gsMkNBQXFCLENBQUE7UUFDckIsdUNBQWlCLENBQUE7UUFDakIsMkNBQXFCLENBQUE7UUFDckIsdUNBQWlCLENBQUE7UUFDakIsMkNBQXFCLENBQUE7SUFDdkIsQ0FBQyxFQVBXLGtCQUFrQixHQUFsQiw0QkFBa0IsS0FBbEIsNEJBQWtCLFFBTzdCO0lBa0JELElBQVkscUJBT1g7SUFQRCxXQUFZLHFCQUFxQjtRQUMvQixvQ0FBVyxDQUFBO1FBQ1gsOENBQXFCLENBQUE7UUFDckIsMENBQWlCLENBQUE7UUFDakIsOENBQXFCLENBQUE7UUFDckIsMENBQWlCLENBQUE7UUFDakIsOENBQXFCLENBQUE7SUFDdkIsQ0FBQyxFQVBXLHFCQUFxQixHQUFyQiwrQkFBcUIsS0FBckIsK0JBQXFCLFFBT2hDO0lBT0QsSUFBWSxjQUtYO0lBTEQsV0FBWSxjQUFjO1FBQ3hCLG1EQUFpQyxDQUFBO1FBQ2pDLDZEQUEyQyxDQUFBO1FBQzNDLCtCQUFhLENBQUE7UUFDYiwrQ0FBNkIsQ0FBQTtJQUMvQixDQUFDLEVBTFcsY0FBYyxHQUFkLHdCQUFjLEtBQWQsd0JBQWMsUUFLekI7SUFjRCxJQUFZLG9CQU9YO0lBUEQsV0FBWSxvQkFBb0I7UUFDOUIsbUNBQVcsQ0FBQTtRQUNYLDZDQUFxQixDQUFBO1FBQ3JCLHlDQUFpQixDQUFBO1FBQ2pCLDZDQUFxQixDQUFBO1FBQ3JCLHlDQUFpQixDQUFBO1FBQ2pCLDZDQUFxQixDQUFBO0lBQ3ZCLENBQUMsRUFQVyxvQkFBb0IsR0FBcEIsOEJBQW9CLEtBQXBCLDhCQUFvQixRQU8vQjtJQUNELElBQVksdUJBSVg7SUFKRCxXQUFZLHVCQUF1QjtRQUNqQyx3Q0FBYSxDQUFBO1FBQ2Isd0NBQWEsQ0FBQTtRQUNiLHNDQUFXLENBQUE7SUFDYixDQUFDLEVBSlcsdUJBQXVCLEdBQXZCLGlDQUF1QixLQUF2QixpQ0FBdUIsUUFJbEM7SUFDRCxJQUFZLGtCQUlYO0lBSkQsV0FBWSxrQkFBa0I7UUFDNUIsaURBQTJCLENBQUE7UUFDM0IsbURBQTZCLENBQUE7UUFDN0IsbURBQTZCLENBQUE7SUFDL0IsQ0FBQyxFQUpXLGtCQUFrQixHQUFsQiw0QkFBa0IsS0FBbEIsNEJBQWtCLFFBSTdCO0lBQ0QsSUFBWSxpQkFJWDtJQUpELFdBQVksaUJBQWlCO1FBQzNCLGdEQUEyQixDQUFBO1FBQzNCLGtEQUE2QixDQUFBO1FBQzdCLGtEQUE2QixDQUFBO0lBQy9CLENBQUMsRUFKVyxpQkFBaUIsR0FBakIsMkJBQWlCLEtBQWpCLDJCQUFpQixRQUk1QjtJQUdELElBQVksYUFNWDtJQU5ELFdBQVksYUFBYTtRQUN2Qiw0QkFBVyxDQUFBO1FBQ1gsb0NBQW1CLENBQUE7UUFDbkIsa0NBQWlCLENBQUE7UUFDakIsOEJBQWEsQ0FBQTtRQUNiLGdDQUFlLENBQUE7SUFDakIsQ0FBQyxFQU5XLGFBQWEsR0FBYix1QkFBYSxLQUFiLHVCQUFhLFFBTXhCO0lBTUQsSUFBWSxhQUVYO0lBRkQsV0FBWSxhQUFhO1FBQ3ZCLDhCQUFhLENBQUE7SUFDZixDQUFDLEVBRlcsYUFBYSxHQUFiLHVCQUFhLEtBQWIsdUJBQWEsUUFFeEI7SUFDRCxJQUFZLDBCQWFYO0lBYkQsV0FBWSwwQkFBMEI7UUFDcEMsK0NBQWlCLENBQUE7UUFDakIsK0NBQWlCLENBQUE7UUFDakIscUVBQXVDLENBQUE7UUFDdkMsaUVBQW1DLENBQUE7UUFDbkMsaUVBQW1DLENBQUE7UUFDbkMsNkRBQStCLENBQUE7UUFDL0IsMkNBQWEsQ0FBQTtRQUNiLGlFQUFtQyxDQUFBO1FBQ25DLDZEQUErQixDQUFBO1FBQy9CLDJDQUFhLENBQUE7UUFDYiw2Q0FBZSxDQUFBO1FBQ2YseUNBQVcsQ0FBQTtJQUNiLENBQUMsRUFiVywwQkFBMEIsR0FBMUIsb0NBQTBCLEtBQTFCLG9DQUEwQixRQWFyQztJQUNELElBQVksc0JBT1g7SUFQRCxXQUFZLHNCQUFzQjtRQUNoQyxxQ0FBVyxDQUFBO1FBQ1gsK0NBQXFCLENBQUE7UUFDckIsMkNBQWlCLENBQUE7UUFDakIsK0NBQXFCLENBQUE7UUFDckIsMkNBQWlCLENBQUE7UUFDakIsK0NBQXFCLENBQUE7SUFDdkIsQ0FBQyxFQVBXLHNCQUFzQixHQUF0QixnQ0FBc0IsS0FBdEIsZ0NBQXNCLFFBT2pDO0lBT0QsSUFBWSxnQkFlWDtJQWZELFdBQVksZ0JBQWdCO1FBQzFCLHFDQUFpQixDQUFBO1FBQ2pCLHFDQUFpQixDQUFBO1FBQ2pCLDJEQUF1QyxDQUFBO1FBQ3ZDLHVEQUFtQyxDQUFBO1FBQ25DLHVEQUFtQyxDQUFBO1FBQ25DLG1EQUErQixDQUFBO1FBQy9CLCtCQUFXLENBQUE7UUFDWCxpQ0FBYSxDQUFBO1FBQ2IsdURBQW1DLENBQUE7UUFDbkMsbURBQStCLENBQUE7UUFDL0IsaUNBQWEsQ0FBQTtRQUNiLG1DQUFlLENBQUE7UUFDZixtQ0FBZSxDQUFBO1FBQ2YsK0JBQVcsQ0FBQTtJQUNiLENBQUMsRUFmVyxnQkFBZ0IsR0FBaEIsMEJBQWdCLEtBQWhCLDBCQUFnQixRQWUzQjtJQUNELElBQVksaUJBZVg7SUFmRCxXQUFZLGlCQUFpQjtRQUMzQixzQ0FBaUIsQ0FBQTtRQUNqQixzQ0FBaUIsQ0FBQTtRQUNqQiw0REFBdUMsQ0FBQTtRQUN2Qyx3REFBbUMsQ0FBQTtRQUNuQyx3REFBbUMsQ0FBQTtRQUNuQyxvREFBK0IsQ0FBQTtRQUMvQixnQ0FBVyxDQUFBO1FBQ1gsa0NBQWEsQ0FBQTtRQUNiLHdEQUFtQyxDQUFBO1FBQ25DLG9EQUErQixDQUFBO1FBQy9CLGtDQUFhLENBQUE7UUFDYixvQ0FBZSxDQUFBO1FBQ2Ysb0NBQWUsQ0FBQTtRQUNmLGdDQUFXLENBQUE7SUFDYixDQUFDLEVBZlcsaUJBQWlCLEdBQWpCLDJCQUFpQixLQUFqQiwyQkFBaUIsUUFlNUI7SUFDRCxJQUFZLGtCQWVYO0lBZkQsV0FBWSxrQkFBa0I7UUFDNUIsdUNBQWlCLENBQUE7UUFDakIsdUNBQWlCLENBQUE7UUFDakIsNkRBQXVDLENBQUE7UUFDdkMseURBQW1DLENBQUE7UUFDbkMseURBQW1DLENBQUE7UUFDbkMscURBQStCLENBQUE7UUFDL0IsaUNBQVcsQ0FBQTtRQUNYLG1DQUFhLENBQUE7UUFDYix5REFBbUMsQ0FBQTtRQUNuQyxxREFBK0IsQ0FBQTtRQUMvQixtQ0FBYSxDQUFBO1FBQ2IscUNBQWUsQ0FBQTtRQUNmLHFDQUFlLENBQUE7UUFDZixpQ0FBVyxDQUFBO0lBQ2IsQ0FBQyxFQWZXLGtCQUFrQixHQUFsQiw0QkFBa0IsS0FBbEIsNEJBQWtCLFFBZTdCO0lBQ0QsSUFBWSxtQkFlWDtJQWZELFdBQVksbUJBQW1CO1FBQzdCLHdDQUFpQixDQUFBO1FBQ2pCLHdDQUFpQixDQUFBO1FBQ2pCLDhEQUF1QyxDQUFBO1FBQ3ZDLDBEQUFtQyxDQUFBO1FBQ25DLDBEQUFtQyxDQUFBO1FBQ25DLHNEQUErQixDQUFBO1FBQy9CLGtDQUFXLENBQUE7UUFDWCxvQ0FBYSxDQUFBO1FBQ2IsMERBQW1DLENBQUE7UUFDbkMsc0RBQStCLENBQUE7UUFDL0Isb0NBQWEsQ0FBQTtRQUNiLHNDQUFlLENBQUE7UUFDZixzQ0FBZSxDQUFBO1FBQ2Ysa0NBQVcsQ0FBQTtJQUNiLENBQUMsRUFmVyxtQkFBbUIsR0FBbkIsNkJBQW1CLEtBQW5CLDZCQUFtQixRQWU5QjtJQUNELElBQVksb0JBZVg7SUFmRCxXQUFZLG9CQUFvQjtRQUM5Qix5Q0FBaUIsQ0FBQTtRQUNqQix5Q0FBaUIsQ0FBQTtRQUNqQiwrREFBdUMsQ0FBQTtRQUN2QywyREFBbUMsQ0FBQTtRQUNuQywyREFBbUMsQ0FBQTtRQUNuQyx1REFBK0IsQ0FBQTtRQUMvQixtQ0FBVyxDQUFBO1FBQ1gscUNBQWEsQ0FBQTtRQUNiLDJEQUFtQyxDQUFBO1FBQ25DLHVEQUErQixDQUFBO1FBQy9CLHFDQUFhLENBQUE7UUFDYix1Q0FBZSxDQUFBO1FBQ2YsdUNBQWUsQ0FBQTtRQUNmLG1DQUFXLENBQUE7SUFDYixDQUFDLEVBZlcsb0JBQW9CLEdBQXBCLDhCQUFvQixLQUFwQiw4QkFBb0IsUUFlL0I7SUF1QkQsSUFBWSx3QkFJWDtJQUpELFdBQVksd0JBQXdCO1FBQ2xDLHlDQUFhLENBQUE7UUFDYix5Q0FBYSxDQUFBO1FBQ2IsNkNBQWlCLENBQUE7SUFDbkIsQ0FBQyxFQUpXLHdCQUF3QixHQUF4QixrQ0FBd0IsS0FBeEIsa0NBQXdCLFFBSW5DO0lBa0JELElBQVksbUJBaUJYO0lBakJELFdBQVksbUJBQW1CO1FBQzdCLGdEQUF5QixDQUFBO1FBQ3pCLDRDQUFxQixDQUFBO1FBQ3JCLGdEQUF5QixDQUFBO1FBQ3pCLGdEQUF5QixDQUFBO1FBQ3pCLHdEQUFpQyxDQUFBO1FBQ2pDLG9EQUE2QixDQUFBO1FBQzdCLGdEQUF5QixDQUFBO1FBQ3pCLDhEQUF1QyxDQUFBO1FBQ3ZDLDREQUFxQyxDQUFBO1FBQ3JDLG9FQUE2QyxDQUFBO1FBQzdDLHNFQUErQyxDQUFBO1FBQy9DLDhEQUF1QyxDQUFBO1FBQ3ZDLDBEQUFtQyxDQUFBO1FBQ25DLDREQUFxQyxDQUFBO1FBQ3JDLGdGQUF5RCxDQUFBO1FBQ3pELHdDQUFpQixDQUFBO0lBQ25CLENBQUMsRUFqQlcsbUJBQW1CLEdBQW5CLDZCQUFtQixLQUFuQiw2QkFBbUIsUUFpQjlCO0lBQ0QsSUFBWSw2QkFLWDtJQUxELFdBQVksNkJBQTZCO1FBQ3ZDLDhDQUFhLENBQUE7UUFDYiwwQ0FBUyxDQUFBO1FBQ1Qsd0VBQXVDLENBQUE7UUFDdkMsNENBQVcsQ0FBQTtJQUNiLENBQUMsRUFMVyw2QkFBNkIsR0FBN0IsdUNBQTZCLEtBQTdCLHVDQUE2QixRQUt4QztJQUNELElBQVksNkJBTVg7SUFORCxXQUFZLDZCQUE2QjtRQUN2Qyw4Q0FBYSxDQUFBO1FBQ2IsMENBQVMsQ0FBQTtRQUNULDhFQUE2QyxDQUFBO1FBQzdDLDRDQUFXLENBQUE7UUFDWCxnRkFBK0MsQ0FBQTtJQUNqRCxDQUFDLEVBTlcsNkJBQTZCLEdBQTdCLHVDQUE2QixLQUE3Qix1Q0FBNkIsUUFNeEM7SUFDRCxJQUFZLHlCQU9YO0lBUEQsV0FBWSx5QkFBeUI7UUFDbkMsd0NBQVcsQ0FBQTtRQUNYLGtEQUFxQixDQUFBO1FBQ3JCLDhDQUFpQixDQUFBO1FBQ2pCLGtEQUFxQixDQUFBO1FBQ3JCLDhDQUFpQixDQUFBO1FBQ2pCLGtEQUFxQixDQUFBO0lBQ3ZCLENBQUMsRUFQVyx5QkFBeUIsR0FBekIsbUNBQXlCLEtBQXpCLG1DQUF5QixRQU9wQztJQUNELElBQVksa0JBaUNYO0lBakNELFdBQVksa0JBQWtCO1FBQzVCLG1DQUFhLENBQUE7UUFDYiwyQ0FBcUIsQ0FBQTtRQUNyQixtQ0FBYSxDQUFBO1FBQ2IsdUNBQWlCLENBQUE7UUFDakIscURBQStCLENBQUE7UUFDL0IsdURBQWlDLENBQUE7UUFDakMsbURBQTZCLENBQUE7UUFDN0IscUNBQWUsQ0FBQTtRQUNmLG1DQUFhLENBQUE7UUFDYiwyREFBcUMsQ0FBQTtRQUNyQyx5REFBbUMsQ0FBQTtRQUNuQyw2REFBdUMsQ0FBQTtRQUN2QywyREFBcUMsQ0FBQTtRQUNyQyxtREFBNkIsQ0FBQTtRQUM3QiwyREFBcUMsQ0FBQTtRQUNyQywyREFBcUMsQ0FBQTtRQUNyQywrQ0FBeUIsQ0FBQTtRQUN6QiwyREFBcUMsQ0FBQTtRQUNyQyx5REFBbUMsQ0FBQTtRQUNuQyxxREFBK0IsQ0FBQTtRQUMvQiw2REFBdUMsQ0FBQTtRQUN2QyxtREFBNkIsQ0FBQTtRQUM3Qix1REFBaUMsQ0FBQTtRQUNqQyxtREFBNkIsQ0FBQTtRQUM3Qiw2REFBdUMsQ0FBQTtRQUN2QywyREFBcUMsQ0FBQTtRQUNyQyx5Q0FBbUIsQ0FBQTtRQUNuQixpRUFBMkMsQ0FBQTtRQUMzQyx5REFBbUMsQ0FBQTtRQUNuQyxpRUFBMkMsQ0FBQTtRQUMzQyx5REFBbUMsQ0FBQTtRQUNuQyxtQ0FBYSxDQUFBO0lBQ2YsQ0FBQyxFQWpDVyxrQkFBa0IsR0FBbEIsNEJBQWtCLEtBQWxCLDRCQUFrQixRQWlDN0I7SUFDRCxJQUFZLHFCQUdYO0lBSEQsV0FBWSxxQkFBcUI7UUFDL0Isa0RBQXlCLENBQUE7UUFDekIsc0NBQWEsQ0FBQTtJQUNmLENBQUMsRUFIVyxxQkFBcUIsR0FBckIsK0JBQXFCLEtBQXJCLCtCQUFxQixRQUdoQztJQUNELElBQVksYUFJWDtJQUpELFdBQVksYUFBYTtRQUN2QixzQ0FBcUIsQ0FBQTtRQUNyQiw4QkFBYSxDQUFBO1FBQ2Isc0NBQXFCLENBQUE7SUFDdkIsQ0FBQyxFQUpXLGFBQWEsR0FBYix1QkFBYSxLQUFiLHVCQUFhLFFBSXhCO0lBQ0QsSUFBWSxtQkFLWDtJQUxELFdBQVksbUJBQW1CO1FBQzdCLDBDQUFtQixDQUFBO1FBQ25CLHdDQUFpQixDQUFBO1FBQ2pCLGtDQUFXLENBQUE7UUFDWCxrQ0FBVyxDQUFBO0lBQ2IsQ0FBQyxFQUxXLG1CQUFtQixHQUFuQiw2QkFBbUIsS0FBbkIsNkJBQW1CLFFBSzlCO0lBQ0QsSUFBWSxzQkFlWDtJQWZELFdBQVksc0JBQXNCO1FBQ2hDLDJDQUFpQixDQUFBO1FBQ2pCLDJDQUFpQixDQUFBO1FBQ2pCLGlFQUF1QyxDQUFBO1FBQ3ZDLDZEQUFtQyxDQUFBO1FBQ25DLDZEQUFtQyxDQUFBO1FBQ25DLHlEQUErQixDQUFBO1FBQy9CLHFDQUFXLENBQUE7UUFDWCx1Q0FBYSxDQUFBO1FBQ2IsNkRBQW1DLENBQUE7UUFDbkMseURBQStCLENBQUE7UUFDL0IsdUNBQWEsQ0FBQTtRQUNiLHlDQUFlLENBQUE7UUFDZix5Q0FBZSxDQUFBO1FBQ2YscUNBQVcsQ0FBQTtJQUNiLENBQUMsRUFmVyxzQkFBc0IsR0FBdEIsZ0NBQXNCLEtBQXRCLGdDQUFzQixRQWVqQztJQUNELElBQVksdUJBZVg7SUFmRCxXQUFZLHVCQUF1QjtRQUNqQyw0Q0FBaUIsQ0FBQTtRQUNqQiw0Q0FBaUIsQ0FBQTtRQUNqQixrRUFBdUMsQ0FBQTtRQUN2Qyw4REFBbUMsQ0FBQTtRQUNuQyw4REFBbUMsQ0FBQTtRQUNuQywwREFBK0IsQ0FBQTtRQUMvQixzQ0FBVyxDQUFBO1FBQ1gsd0NBQWEsQ0FBQTtRQUNiLDhEQUFtQyxDQUFBO1FBQ25DLDBEQUErQixDQUFBO1FBQy9CLHdDQUFhLENBQUE7UUFDYiwwQ0FBZSxDQUFBO1FBQ2YsMENBQWUsQ0FBQTtRQUNmLHNDQUFXLENBQUE7SUFDYixDQUFDLEVBZlcsdUJBQXVCLEdBQXZCLGlDQUF1QixLQUF2QixpQ0FBdUIsUUFlbEM7SUFDRCxJQUFZLGdCQUlYO0lBSkQsV0FBWSxnQkFBZ0I7UUFDMUIsK0NBQTJCLENBQUE7UUFDM0IsaURBQTZCLENBQUE7UUFDN0IsaURBQTZCLENBQUE7SUFDL0IsQ0FBQyxFQUpXLGdCQUFnQixHQUFoQiwwQkFBZ0IsS0FBaEIsMEJBQWdCLFFBSTNCO0lBQ0QsSUFBWSxjQUdYO0lBSEQsV0FBWSxjQUFjO1FBQ3hCLDJDQUF5QixDQUFBO1FBQ3pCLGlEQUErQixDQUFBO0lBQ2pDLENBQUMsRUFIVyxjQUFjLEdBQWQsd0JBQWMsS0FBZCx3QkFBYyxRQUd6QjtJQUNELElBQVksZUFJWDtJQUpELFdBQVksZUFBZTtRQUN6Qiw4Q0FBMkIsQ0FBQTtRQUMzQixnREFBNkIsQ0FBQTtRQUM3QixnREFBNkIsQ0FBQTtJQUMvQixDQUFDLEVBSlcsZUFBZSxHQUFmLHlCQUFlLEtBQWYseUJBQWUsUUFJMUI7SUF1QkQsSUFBWSxzQkFFWDtJQUZELFdBQVksc0JBQXNCO1FBQ2hDLDZEQUFtQyxDQUFBO0lBQ3JDLENBQUMsRUFGVyxzQkFBc0IsR0FBdEIsZ0NBQXNCLEtBQXRCLGdDQUFzQixRQUVqQztJQUtELElBQVksY0FFWDtJQUZELFdBQVksY0FBYztRQUN4Qix1Q0FBcUIsQ0FBQTtJQUN2QixDQUFDLEVBRlcsY0FBYyxHQUFkLHdCQUFjLEtBQWQsd0JBQWMsUUFFekI7SUFLRCxJQUFZLGVBR1g7SUFIRCxXQUFZLGVBQWU7UUFDekIsNENBQXlCLENBQUE7UUFDekIsd0NBQXFCLENBQUE7SUFDdkIsQ0FBQyxFQUhXLGVBQWUsR0FBZix5QkFBZSxLQUFmLHlCQUFlLFFBRzFCO0lBQ0QsSUFBWSxnQkFHWDtJQUhELFdBQVksZ0JBQWdCO1FBQzFCLDZDQUF5QixDQUFBO1FBQ3pCLHlDQUFxQixDQUFBO0lBQ3ZCLENBQUMsRUFIVyxnQkFBZ0IsR0FBaEIsMEJBQWdCLEtBQWhCLDBCQUFnQixRQUczQjtJQUNELElBQVksa0JBSVg7SUFKRCxXQUFZLGtCQUFrQjtRQUM1Qix1Q0FBaUIsQ0FBQTtRQUNqQiwyREFBcUMsQ0FBQTtRQUNyQyxxQ0FBZSxDQUFBO0lBQ2pCLENBQUMsRUFKVyxrQkFBa0IsR0FBbEIsNEJBQWtCLEtBQWxCLDRCQUFrQixRQUk3QjtJQUNELElBQVksK0JBS1g7SUFMRCxXQUFZLCtCQUErQjtRQUN6Qyw4Q0FBVyxDQUFBO1FBQ1gsMERBQXVCLENBQUE7UUFDdkIsZ0RBQWEsQ0FBQTtRQUNiLDBEQUF1QixDQUFBO0lBQ3pCLENBQUMsRUFMVywrQkFBK0IsR0FBL0IseUNBQStCLEtBQS9CLHlDQUErQixRQUsxQztJQUNELElBQVksOEJBT1g7SUFQRCxXQUFZLDhCQUE4QjtRQUN4Qyw2Q0FBVyxDQUFBO1FBQ1gsdURBQXFCLENBQUE7UUFDckIsbURBQWlCLENBQUE7UUFDakIsdURBQXFCLENBQUE7UUFDckIsbURBQWlCLENBQUE7UUFDakIsdURBQXFCLENBQUE7SUFDdkIsQ0FBQyxFQVBXLDhCQUE4QixHQUE5Qix3Q0FBOEIsS0FBOUIsd0NBQThCLFFBT3pDO0lBbUJELElBQVksb0JBT1g7SUFQRCxXQUFZLG9CQUFvQjtRQUM5QixtQ0FBVyxDQUFBO1FBQ1gsNkNBQXFCLENBQUE7UUFDckIseUNBQWlCLENBQUE7UUFDakIsNkNBQXFCLENBQUE7UUFDckIseUNBQWlCLENBQUE7UUFDakIsNkNBQXFCLENBQUE7SUFDdkIsQ0FBQyxFQVBXLG9CQUFvQixHQUFwQiw4QkFBb0IsS0FBcEIsOEJBQW9CLFFBTy9CO0lBcUJELElBQVksYUFTWDtJQVRELFdBQVksYUFBYTtRQUN2QixrQ0FBaUIsQ0FBQTtRQUNqQiwwQ0FBeUIsQ0FBQTtRQUN6Qiw4Q0FBNkIsQ0FBQTtRQUM3Qix3Q0FBdUIsQ0FBQTtRQUN2QixrQ0FBaUIsQ0FBQTtRQUNqQixzQ0FBcUIsQ0FBQTtRQUNyQixnQ0FBZSxDQUFBO1FBQ2Ysa0NBQWlCLENBQUE7SUFDbkIsQ0FBQyxFQVRXLGFBQWEsR0FBYix1QkFBYSxLQUFiLHVCQUFhLFFBU3hCO0lBQ0QsSUFBWSx5QkFRWDtJQVJELFdBQVkseUJBQXlCO1FBQ25DLDhDQUFpQixDQUFBO1FBQ2pCLHdDQUFXLENBQUE7UUFDWCwwQ0FBYSxDQUFBO1FBQ2IsMENBQWEsQ0FBQTtRQUNiLDRDQUFlLENBQUE7UUFDZiw0Q0FBZSxDQUFBO1FBQ2Ysd0NBQVcsQ0FBQTtJQUNiLENBQUMsRUFSVyx5QkFBeUIsR0FBekIsbUNBQXlCLEtBQXpCLG1DQUF5QixRQVFwQztJQUlELElBQVksa0JBS1g7SUFMRCxXQUFZLGtCQUFrQjtRQUM1QixpREFBMkIsQ0FBQTtRQUMzQixxREFBK0IsQ0FBQTtRQUMvQixtREFBNkIsQ0FBQTtRQUM3Qix1REFBaUMsQ0FBQTtJQUNuQyxDQUFDLEVBTFcsa0JBQWtCLEdBQWxCLDRCQUFrQixLQUFsQiw0QkFBa0IsUUFLN0I7SUFRRCxJQUFZLDZCQU9YO0lBUEQsV0FBWSw2QkFBNkI7UUFDdkMsNENBQVcsQ0FBQTtRQUNYLHNEQUFxQixDQUFBO1FBQ3JCLGtEQUFpQixDQUFBO1FBQ2pCLHNEQUFxQixDQUFBO1FBQ3JCLGtEQUFpQixDQUFBO1FBQ2pCLHNEQUFxQixDQUFBO0lBQ3ZCLENBQUMsRUFQVyw2QkFBNkIsR0FBN0IsdUNBQTZCLEtBQTdCLHVDQUE2QixRQU94QztJQUdELElBQVkscUJBS1g7SUFMRCxXQUFZLHFCQUFxQjtRQUMvQixnREFBdUIsQ0FBQTtRQUN2QixvQ0FBVyxDQUFBO1FBQ1gsMENBQWlCLENBQUE7UUFDakIsc0NBQWEsQ0FBQTtJQUNmLENBQUMsRUFMVyxxQkFBcUIsR0FBckIsK0JBQXFCLEtBQXJCLCtCQUFxQixRQUtoQztJQVVELElBQVksZUFLWDtJQUxELFdBQVksZUFBZTtRQUN6Qiw4Q0FBMkIsQ0FBQTtRQUMzQixnQ0FBYSxDQUFBO1FBQ2IsZ0RBQTZCLENBQUE7UUFDN0IsOERBQTJDLENBQUE7SUFDN0MsQ0FBQyxFQUxXLGVBQWUsR0FBZix5QkFBZSxLQUFmLHlCQUFlLFFBSzFCO0lBb0NELElBQVksaUJBUVg7SUFSRCxXQUFZLGlCQUFpQjtRQUMzQixzQ0FBaUIsQ0FBQTtRQUNqQix3Q0FBbUIsQ0FBQTtRQUNuQix3Q0FBbUIsQ0FBQTtRQUNuQix3Q0FBbUIsQ0FBQTtRQUNuQiw0Q0FBdUIsQ0FBQTtRQUN2Qix3Q0FBbUIsQ0FBQTtRQUNuQiw0Q0FBdUIsQ0FBQTtJQUN6QixDQUFDLEVBUlcsaUJBQWlCLEdBQWpCLDJCQUFpQixLQUFqQiwyQkFBaUIsUUFRNUI7SUFNRCxJQUFZLGlCQVNYO0lBVEQsV0FBWSxpQkFBaUI7UUFDM0Isc0NBQWlCLENBQUE7UUFDakIsZ0RBQTJCLENBQUE7UUFDM0Isc0RBQWlDLENBQUE7UUFDakMsc0RBQWlDLENBQUE7UUFDakMsd0NBQW1CLENBQUE7UUFDbkIsc0NBQWlCLENBQUE7UUFDakIsZ0NBQVcsQ0FBQTtRQUNYLGdDQUFXLENBQUE7SUFDYixDQUFDLEVBVFcsaUJBQWlCLEdBQWpCLDJCQUFpQixLQUFqQiwyQkFBaUIsUUFTNUI7SUFnRUQsSUFBWSxpQkFPWDtJQVBELFdBQVksaUJBQWlCO1FBQzNCLGdDQUFXLENBQUE7UUFDWCwwQ0FBcUIsQ0FBQTtRQUNyQixzQ0FBaUIsQ0FBQTtRQUNqQiwwQ0FBcUIsQ0FBQTtRQUNyQixzQ0FBaUIsQ0FBQTtRQUNqQiwwQ0FBcUIsQ0FBQTtJQUN2QixDQUFDLEVBUFcsaUJBQWlCLEdBQWpCLDJCQUFpQixLQUFqQiwyQkFBaUIsUUFPNUI7SUFDRCxJQUFZLG9CQU9YO0lBUEQsV0FBWSxvQkFBb0I7UUFDOUIsbUNBQVcsQ0FBQTtRQUNYLDZDQUFxQixDQUFBO1FBQ3JCLHlDQUFpQixDQUFBO1FBQ2pCLDZDQUFxQixDQUFBO1FBQ3JCLHlDQUFpQixDQUFBO1FBQ2pCLDZDQUFxQixDQUFBO0lBQ3ZCLENBQUMsRUFQVyxvQkFBb0IsR0FBcEIsOEJBQW9CLEtBQXBCLDhCQUFvQixRQU8vQjtJQStCRCxJQUFZLGlCQU9YO0lBUEQsV0FBWSxpQkFBaUI7UUFDM0IsZ0NBQVcsQ0FBQTtRQUNYLDBDQUFxQixDQUFBO1FBQ3JCLHNDQUFpQixDQUFBO1FBQ2pCLDBDQUFxQixDQUFBO1FBQ3JCLHNDQUFpQixDQUFBO1FBQ2pCLDBDQUFxQixDQUFBO0lBQ3ZCLENBQUMsRUFQVyxpQkFBaUIsR0FBakIsMkJBQWlCLEtBQWpCLDJCQUFpQixRQU81QjtJQUNELElBQVksa0JBSVg7SUFKRCxXQUFZLGtCQUFrQjtRQUM1QixtREFBNkIsQ0FBQTtRQUM3QiwyQ0FBcUIsQ0FBQTtRQUNyQix1Q0FBaUIsQ0FBQTtJQUNuQixDQUFDLEVBSlcsa0JBQWtCLEdBQWxCLDRCQUFrQixLQUFsQiw0QkFBa0IsUUFJN0I7SUFDRCxJQUFZLDZCQUlYO0lBSkQsV0FBWSw2QkFBNkI7UUFDdkMsb0VBQW1DLENBQUE7UUFDbkMsOENBQWEsQ0FBQTtRQUNiLGdEQUFlLENBQUE7SUFDakIsQ0FBQyxFQUpXLDZCQUE2QixHQUE3Qix1Q0FBNkIsS0FBN0IsdUNBQTZCLFFBSXhDO0lBaUlELElBQVksY0FJWDtJQUpELFdBQVksY0FBYztRQUN4QiwrQkFBYSxDQUFBO1FBQ2IseUNBQXVCLENBQUE7UUFDdkIscUNBQW1CLENBQUE7SUFDckIsQ0FBQyxFQUpXLGNBQWMsR0FBZCx3QkFBYyxLQUFkLHdCQUFjLFFBSXpCO0FBR0gsQ0FBQyxFQTc4QmdCLFNBQVMsR0FBVCxpQkFBUyxLQUFULGlCQUFTLFFBNjhCekI7QUFDRCxXQUFXO0FBQ0UsUUFBQSxtQkFBbUIsR0FBRyxVQUNqQyxVQUE0RSxFQUM1RSxRQUE2RDtJQUU3RCxPQUFPLGlCQUFPLENBQUMscUJBQXFCLEVBQUUsVUFBVSxFQUFFLFFBQVEsQ0FBQyxDQUFDO0FBQzlELENBQUMsQ0FBQztBQUNXLFFBQUEsV0FBVyxHQUFHLFVBQ3pCLFVBQW9FLEVBQ3BFLFFBQTZEO0lBRTdELE9BQU8saUJBQU8sQ0FBQyxhQUFhLEVBQUUsVUFBVSxFQUFFLFFBQVEsQ0FBQyxDQUFDO0FBQ3RELENBQUMsQ0FBQztBQUNXLFFBQUEsSUFBSSxHQUFHLFVBQ2xCLFVBQTZELEVBQzdELFFBQTZEO0lBRTdELE9BQU8saUJBQU8sQ0FBQyxNQUFNLEVBQUUsVUFBVSxFQUFFLFFBQVEsQ0FBQyxDQUFDO0FBQy9DLENBQUMsQ0FBQztBQUNXLFFBQUEsVUFBVSxHQUFHLFVBQ3hCLFVBQW1FLEVBQ25FLFFBQTZEO0lBRTdELE9BQU8saUJBQU8sQ0FBQyxZQUFZLEVBQUUsVUFBVSxFQUFFLFFBQVEsQ0FBQyxDQUFDO0FBQ3JELENBQUMsQ0FBQztBQUNXLFFBQUEsUUFBUSxHQUFHLFVBQ3RCLFVBQWlFLEVBQ2pFLFFBQTZEO0lBRTdELE9BQU8saUJBQU8sQ0FBQyxVQUFVLEVBQUUsVUFBVSxFQUFFLFFBQVEsQ0FBQyxDQUFDO0FBQ25ELENBQUMsQ0FBQztBQUNXLFFBQUEsY0FBYyxHQUFHLFVBQzVCLFVBQXVFLEVBQ3ZFLFFBQTZEO0lBRTdELE9BQU8saUJBQU8sQ0FBQyxnQkFBZ0IsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDekQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxhQUFhLEdBQUcsVUFDM0IsVUFBc0UsRUFDdEUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLGVBQWUsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDeEQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxVQUFVLEdBQUcsVUFDeEIsVUFBbUUsRUFDbkUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLFlBQVksRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDckQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxPQUFPLEdBQUcsVUFDckIsVUFBZ0UsRUFDaEUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLFNBQVMsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDbEQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxRQUFRLEdBQUcsVUFDdEIsVUFBaUUsRUFDakUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLFVBQVUsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDbkQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxPQUFPLEdBQUcsVUFDckIsVUFBZ0UsRUFDaEUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLFNBQVMsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDbEQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxVQUFVLEdBQUcsVUFDeEIsVUFBbUUsRUFDbkUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLFlBQVksRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDckQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxXQUFXLEdBQUcsVUFDekIsVUFBb0UsRUFDcEUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLGFBQWEsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDdEQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxZQUFZLEdBQUcsVUFDMUIsVUFBcUUsRUFDckUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLGNBQWMsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDdkQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxVQUFVLEdBQUcsVUFDeEIsVUFBbUUsRUFDbkUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLFlBQVksRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDckQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxTQUFTLEdBQUcsVUFDdkIsVUFBa0UsRUFDbEUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLFdBQVcsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDcEQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxVQUFVLEdBQUcsVUFDeEIsVUFBbUUsRUFDbkUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLFlBQVksRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDckQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxlQUFlLEdBQUcsVUFDN0IsVUFBd0UsRUFDeEUsUUFB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QUFjLEVBQUUsVUFBVSxFQUFFLFFBQVEsQ0FBQyxDQUFDO0FBQ3ZELENBQUMsQ0FBQztBQUNXLFFBQUEsU0FBUyxHQUFHLFVBQ3ZCLFVBQWtFLEVBQ2xFLFFBQTZEO0lBRTdELE9BQU8saUJBQU8sQ0FBQyxXQUFXLEVBQUUsVUFBVSxFQUFFLFFBQVEsQ0FBQyxDQUFDO0FBQ3BELENBQUMsQ0FBQztBQUNXLFFBQUEsV0FBVyxHQUFHLFVBQ3pCLFVBQW9FLEVBQ3BFLFFBQTZEO0lBRTdELE9BQU8saUJBQU8sQ0FBQyxhQUFhLEVBQUUsVUFBVSxFQUFFLFFBQVEsQ0FBQyxDQUFDO0FBQ3RELENBQUMsQ0FBQztBQUNXLFFBQUEsV0FBVyxHQUFHLFVBQ3pCLFVBQW9FLEVBQ3BFLFFBQTZEO0lBRTdELE9BQU8saUJBQU8sQ0FBQyxhQUFhLEVBQUUsVUFBVSxFQUFFLFFBQVEsQ0FBQyxDQUFDO0FBQ3RELENBQUMsQ0FBQztBQUNXLFFBQUEsU0FBUyxHQUFHLFVBQ3ZCLFVBQWtFLEVBQ2xFLFFBQTZEO0lBRTdELE9BQU8saUJBQU8sQ0FBQyxXQUFXLEVBQUUsVUFBVSxFQUFFLFFBQVEsQ0FBQyxDQUFDO0FBQ3BELENBQUMsQ0FBQztBQUNXLFFBQUEsb0JBQW9CLEdBQUcsVUFDbEMsVUFBNkUsRUFDN0UsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLHNCQUFzQixFQUFFLFVBQVUsRUFBRSxRQUFRLENBQUMsQ0FBQztBQUMvRCxDQUFDLENBQUM7QUFDVyxRQUFBLE1BQU0sR0FBRyxVQUNwQixVQUErRCxFQUMvRCxRQUE2RDtJQUU3RCxPQUFPLGlCQUFPLENBQUMsUUFBUSxFQUFFLFVBQVUsRUFBRSxRQUFRLENBQUMsQ0FBQztBQUNqRCxDQUFDLENBQUMiLCJuYW1lcyI6W10sInNvdXJjZXMiOlsiL1VzZXJzL2RlcmsvanNQcm9qZWN0cy9zb3VyY2VyZXIvanMvbWFpbi50cyJdLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgeyBFbGVtZW50Tm9kZSwgZWxlbWVudCB9IGZyb20gJy4vZWxlbWVudCc7XG5pbXBvcnQgeyBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMgfSBmcm9tICcuL2xheW91dHBhcmFtcyc7XG4vLyB0eXBlc1xuLyogZ2VuZXJhdGVkIEAgMjAxOC0xMS0xMlQxMzo0MToxNC40ODYgKi9cbmV4cG9ydCBuYW1lc3BhY2UgTWFpblR5cGVzIHtcbiAgZXhwb3J0IGludGVyZmFjZSBBYnNMaXN0Vmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyBBZGFwdGVyVmlld0F0dHJpYnV0ZXMge1xuICAgIGNhY2hlQ29sb3JIaW50Pzogc3RyaW5nO1xuICAgIGNob2ljZU1vZGU/OiBDaG9pY2VNb2RlRW51bTtcbiAgICBkcmF3U2VsZWN0b3JPblRvcD86IGJvb2xlYW47XG4gICAgZmFzdFNjcm9sbEFsd2F5c1Zpc2libGU/OiBib29sZWFuO1xuICAgIGZhc3RTY3JvbGxFbmFibGVkPzogYm9vbGVhbjtcbiAgICBmYXN0U2Nyb2xsU3R5bGU/OiBzdHJpbmc7XG4gICAgbGlzdFNlbGVjdG9yPzogc3RyaW5nO1xuICAgIHNjcm9sbGluZ0NhY2hlPzogYm9vbGVhbjtcbiAgICBzbW9vdGhTY3JvbGxiYXI/OiBib29sZWFuO1xuICAgIHN0YWNrRnJvbUJvdHRvbT86IGJvb2xlYW47XG4gICAgdGV4dEZpbHRlckVuYWJsZWQ/OiBib29sZWFuO1xuICAgIHRyYW5zY3JpcHRNb2RlPzogVHJhbnNjcmlwdE1vZGVFbnVtO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgQWJzTGlzdFZpZXdMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0dyb3VwTGF5b3V0UGFyYW1zQXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBBYnNTZWVrQmFyQXR0cmlidXRlcyBleHRlbmRzIFByb2dyZXNzQmFyQXR0cmlidXRlcyB7XG4gICAgU2Vla0Jhcl9zcGxpdFRyYWNrPzogYm9vbGVhbjtcbiAgICBTZWVrQmFyX3RodW1iPzogbnVtYmVyO1xuICAgIFNlZWtCYXJfdGh1bWJPZmZzZXQ/OiBzdHJpbmc7XG4gICAgU2Vla0Jhcl90aHVtYlRpbnQ/OiBudW1iZXI7XG4gICAgU2Vla0Jhcl90aHVtYlRpbnRNb2RlPzogbnVtYmVyO1xuICAgIFNlZWtCYXJfdGlja01hcms/OiBudW1iZXI7XG4gICAgU2Vla0Jhcl90aWNrTWFya1RpbnQ/OiBudW1iZXI7XG4gICAgU2Vla0Jhcl90aWNrTWFya1RpbnRNb2RlPzogbnVtYmVyO1xuICAgIHRodW1iVGludD86IHN0cmluZztcbiAgICB0aHVtYlRpbnRNb2RlPzogVGh1bWJUaW50TW9kZUVudW07XG4gICAgdGlja01hcmtUaW50Pzogc3RyaW5nO1xuICAgIHRpY2tNYXJrVGludE1vZGU/OiBUaWNrTWFya1RpbnRNb2RlRW51bTtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEFic1NwaW5uZXJBdHRyaWJ1dGVzIGV4dGVuZHMgQWRhcHRlclZpZXdBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgZW51bSBBY2Nlc3NpYmlsaXR5TGl2ZVJlZ2lvbkVudW0ge1xuICAgIGFzc2VydGl2ZSA9ICdhc3NlcnRpdmUnLFxuICAgIG5vbmUgPSAnbm9uZScsXG4gICAgcG9saXRlID0gJ3BvbGl0ZSdcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEFjdGlvbk1lbnVWaWV3QXR0cmlidXRlcyBleHRlbmRzIExpbmVhckxheW91dEF0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgQWN0aW9uTWVudVZpZXdMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIGV4dGVuZHMgTGluZWFyTGF5b3V0TGF5b3V0UGFyYW1zQXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBBZGFwdGVyVmlld0FuaW1hdG9yQXR0cmlidXRlcyBleHRlbmRzIEFkYXB0ZXJWaWV3QXR0cmlidXRlcyB7XG4gICAgYW5pbWF0ZUZpcnN0Vmlldz86IGJvb2xlYW47XG4gICAgaW5BbmltYXRpb24/OiBudW1iZXI7XG4gICAgb3V0QW5pbWF0aW9uPzogbnVtYmVyO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgQWRhcHRlclZpZXdBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0dyb3VwQXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBBZGFwdGVyVmlld0ZsaXBwZXJBdHRyaWJ1dGVzIGV4dGVuZHMgQWRhcHRlclZpZXdBbmltYXRvckF0dHJpYnV0ZXMge1xuICAgIGF1dG9TdGFydD86IGJvb2xlYW47XG4gICAgZmxpcEludGVydmFsPzogbnVtYmVyO1xuICB9XG4gIGV4cG9ydCBlbnVtIEFsaWdubWVudE1vZGVFbnVtIHtcbiAgICBhbGlnbkJvdW5kcyA9ICdhbGlnbkJvdW5kcycsXG4gICAgYWxpZ25NYXJnaW5zID0gJ2FsaWduTWFyZ2lucydcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEF1dG9Db21wbGV0ZVRleHRWaWV3QXR0cmlidXRlcyBleHRlbmRzIEVkaXRUZXh0QXR0cmlidXRlcyB7XG4gICAgY29tcGxldGlvbkhpbnQ/OiBzdHJpbmc7XG4gICAgY29tcGxldGlvblRocmVzaG9sZD86IG51bWJlcjtcbiAgICBkcm9wRG93bkFuY2hvcj86IHN0cmluZztcbiAgICBkcm9wRG93bkhlaWdodD86IHN0cmluZyB8IERyb3BEb3duSGVpZ2h0RW51bTtcbiAgICBkcm9wRG93bkhvcml6b250YWxPZmZzZXQ/OiBudW1iZXI7XG4gICAgZHJvcERvd25WZXJ0aWNhbE9mZnNldD86IG51bWJlcjtcbiAgICBkcm9wRG93bldpZHRoPzogc3RyaW5nIHwgRHJvcERvd25XaWR0aEVudW07XG4gIH1cbiAgZXhwb3J0IGVudW0gQXV0b0xpbmtGbGFnc0VudW0ge1xuICAgIGFsbCA9ICdhbGwnLFxuICAgIGVtYWlsID0gJ2VtYWlsJyxcbiAgICBtYXAgPSAnbWFwJyxcbiAgICBub25lID0gJ25vbmUnLFxuICAgIHBob25lID0gJ3Bob25lJyxcbiAgICB3ZWIgPSAnd2ViJ1xuICB9XG4gIGV4cG9ydCBlbnVtIEF1dG9TaXplVGV4dFR5cGVFbnVtIHtcbiAgICBub25lID0gJ25vbmUnLFxuICAgIHVuaWZvcm0gPSAndW5pZm9ybSdcbiAgfVxuICBleHBvcnQgZW51bSBCYWNrZ3JvdW5kVGludE1vZGVFbnVtIHtcbiAgICBhZGQgPSAnYWRkJyxcbiAgICBtdWx0aXBseSA9ICdtdWx0aXBseScsXG4gICAgc2NyZWVuID0gJ3NjcmVlbicsXG4gICAgc3JjX2F0b3AgPSAnc3JjX2F0b3AnLFxuICAgIHNyY19pbiA9ICdzcmNfaW4nLFxuICAgIHNyY19vdmVyID0gJ3NyY19vdmVyJ1xuICB9XG4gIGV4cG9ydCBlbnVtIEJyZWFrU3RyYXRlZ3lFbnVtIHtcbiAgICBiYWxhbmNlZCA9ICdiYWxhbmNlZCcsXG4gICAgaGlnaF9xdWFsaXR5ID0gJ2hpZ2hfcXVhbGl0eScsXG4gICAgc2ltcGxlID0gJ3NpbXBsZSdcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEJ1dHRvbkF0dHJpYnV0ZXMgZXh0ZW5kcyBUZXh0Vmlld0F0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBlbnVtIEJ1dHRvblRpbnRNb2RlRW51bSB7XG4gICAgYWRkID0gJ2FkZCcsXG4gICAgbXVsdGlwbHkgPSAnbXVsdGlwbHknLFxuICAgIHNjcmVlbiA9ICdzY3JlZW4nLFxuICAgIHNyY19hdG9wID0gJ3NyY19hdG9wJyxcbiAgICBzcmNfaW4gPSAnc3JjX2luJyxcbiAgICBzcmNfb3ZlciA9ICdzcmNfb3ZlcidcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIENhbGVuZGFyVmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyBGcmFtZUxheW91dEF0dHJpYnV0ZXMge1xuICAgIGRhdGVUZXh0QXBwZWFyYW5jZT86IHN0cmluZztcbiAgICBmaXJzdERheU9mV2Vlaz86IG51bWJlcjtcbiAgICBmb2N1c2VkTW9udGhEYXRlQ29sb3I/OiBzdHJpbmc7XG4gICAgbWF4RGF0ZT86IG51bWJlcjtcbiAgICBtaW5EYXRlPzogbnVtYmVyO1xuICAgIHNlbGVjdGVkRGF0ZVZlcnRpY2FsQmFyPzogc3RyaW5nO1xuICAgIHNlbGVjdGVkV2Vla0JhY2tncm91bmRDb2xvcj86IHN0cmluZztcbiAgICBzaG93V2Vla051bWJlcj86IGJvb2xlYW47XG4gICAgc2hvd25XZWVrQ291bnQ/OiBudW1iZXI7XG4gICAgdW5mb2N1c2VkTW9udGhEYXRlQ29sb3I/OiBzdHJpbmc7XG4gICAgd2Vla0RheVRleHRBcHBlYXJhbmNlPzogc3RyaW5nO1xuICAgIHdlZWtOdW1iZXJDb2xvcj86IHN0cmluZztcbiAgICB3ZWVrU2VwYXJhdG9yTGluZUNvbG9yPzogc3RyaW5nO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgQ2hlY2tCb3hBdHRyaWJ1dGVzIGV4dGVuZHMgQ29tcG91bmRCdXR0b25BdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgZW51bSBDaGVja01hcmtUaW50TW9kZUVudW0ge1xuICAgIGFkZCA9ICdhZGQnLFxuICAgIG11bHRpcGx5ID0gJ211bHRpcGx5JyxcbiAgICBzY3JlZW4gPSAnc2NyZWVuJyxcbiAgICBzcmNfYXRvcCA9ICdzcmNfYXRvcCcsXG4gICAgc3JjX2luID0gJ3NyY19pbicsXG4gICAgc3JjX292ZXIgPSAnc3JjX292ZXInXG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBDaGVja2VkVGV4dFZpZXdBdHRyaWJ1dGVzIGV4dGVuZHMgVGV4dFZpZXdBdHRyaWJ1dGVzIHtcbiAgICBjaGVja01hcms/OiBzdHJpbmc7XG4gICAgY2hlY2tNYXJrVGludD86IHN0cmluZztcbiAgICBjaGVja01hcmtUaW50TW9kZT86IENoZWNrTWFya1RpbnRNb2RlRW51bTtcbiAgICBjaGVja2VkPzogYm9vbGVhbjtcbiAgfVxuICBleHBvcnQgZW51bSBDaG9pY2VNb2RlRW51bSB7XG4gICAgbXVsdGlwbGVDaG9pY2UgPSAnbXVsdGlwbGVDaG9pY2UnLFxuICAgIG11bHRpcGxlQ2hvaWNlTW9kYWwgPSAnbXVsdGlwbGVDaG9pY2VNb2RhbCcsXG4gICAgbm9uZSA9ICdub25lJyxcbiAgICBzaW5nbGVDaG9pY2UgPSAnc2luZ2xlQ2hvaWNlJ1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgQ2hyb25vbWV0ZXJBdHRyaWJ1dGVzIGV4dGVuZHMgVGV4dFZpZXdBdHRyaWJ1dGVzIHtcbiAgICBjb3VudERvd24/OiBib29sZWFuO1xuICAgIGZvcm1hdD86IHN0cmluZztcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIENvbXBvdW5kQnV0dG9uQXR0cmlidXRlcyBleHRlbmRzIEJ1dHRvbkF0dHJpYnV0ZXMge1xuICAgIGJ1dHRvbj86IHN0cmluZztcbiAgICBidXR0b25UaW50Pzogc3RyaW5nO1xuICAgIGJ1dHRvblRpbnRNb2RlPzogQnV0dG9uVGludE1vZGVFbnVtO1xuICAgIGNoZWNrZWQ/OiBib29sZWFuO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgRGF0ZVBpY2tlckF0dHJpYnV0ZXMgZXh0ZW5kcyBGcmFtZUxheW91dEF0dHJpYnV0ZXMge1xuICAgIGZpcnN0RGF5T2ZXZWVrPzogbnVtYmVyO1xuICB9XG4gIGV4cG9ydCBlbnVtIERyYXdhYmxlVGludE1vZGVFbnVtIHtcbiAgICBhZGQgPSAnYWRkJyxcbiAgICBtdWx0aXBseSA9ICdtdWx0aXBseScsXG4gICAgc2NyZWVuID0gJ3NjcmVlbicsXG4gICAgc3JjX2F0b3AgPSAnc3JjX2F0b3AnLFxuICAgIHNyY19pbiA9ICdzcmNfaW4nLFxuICAgIHNyY19vdmVyID0gJ3NyY19vdmVyJ1xuICB9XG4gIGV4cG9ydCBlbnVtIERyYXdpbmdDYWNoZVF1YWxpdHlFbnVtIHtcbiAgICBhdXRvID0gJ2F1dG8nLFxuICAgIGhpZ2ggPSAnaGlnaCcsXG4gICAgbG93ID0gJ2xvdydcbiAgfVxuICBleHBvcnQgZW51bSBEcm9wRG93bkhlaWdodEVudW0ge1xuICAgIGZpbGxfcGFyZW50ID0gJ2ZpbGxfcGFyZW50JyxcbiAgICBtYXRjaF9wYXJlbnQgPSAnbWF0Y2hfcGFyZW50JyxcbiAgICB3cmFwX2NvbnRlbnQgPSAnd3JhcF9jb250ZW50J1xuICB9XG4gIGV4cG9ydCBlbnVtIERyb3BEb3duV2lkdGhFbnVtIHtcbiAgICBmaWxsX3BhcmVudCA9ICdmaWxsX3BhcmVudCcsXG4gICAgbWF0Y2hfcGFyZW50ID0gJ21hdGNoX3BhcmVudCcsXG4gICAgd3JhcF9jb250ZW50ID0gJ3dyYXBfY29udGVudCdcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEVkaXRUZXh0QXR0cmlidXRlcyBleHRlbmRzIFRleHRWaWV3QXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGVudW0gRWxsaXBzaXplRW51bSB7XG4gICAgZW5kID0gJ2VuZCcsXG4gICAgbWFycXVlZSA9ICdtYXJxdWVlJyxcbiAgICBtaWRkbGUgPSAnbWlkZGxlJyxcbiAgICBub25lID0gJ25vbmUnLFxuICAgIHN0YXJ0ID0gJ3N0YXJ0J1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgRXhwYW5kYWJsZUxpc3RWaWV3QXR0cmlidXRlcyBleHRlbmRzIExpc3RWaWV3QXR0cmlidXRlcyB7XG4gICAgY2hpbGREaXZpZGVyPzogc3RyaW5nO1xuICAgIGNoaWxkSW5kaWNhdG9yPzogc3RyaW5nO1xuICAgIGdyb3VwSW5kaWNhdG9yPzogc3RyaW5nO1xuICB9XG4gIGV4cG9ydCBlbnVtIEZvY3VzYWJsZUVudW0ge1xuICAgIGF1dG8gPSAnYXV0bydcbiAgfVxuICBleHBvcnQgZW51bSBGb3JlZ3JvdW5kR3Jhdml0eUZsYWdzRW51bSB7XG4gICAgYm90dG9tID0gJ2JvdHRvbScsXG4gICAgY2VudGVyID0gJ2NlbnRlcicsXG4gICAgY2VudGVyX2hvcml6b250YWwgPSAnY2VudGVyX2hvcml6b250YWwnLFxuICAgIGNlbnRlcl92ZXJ0aWNhbCA9ICdjZW50ZXJfdmVydGljYWwnLFxuICAgIGNsaXBfaG9yaXpvbnRhbCA9ICdjbGlwX2hvcml6b250YWwnLFxuICAgIGNsaXBfdmVydGljYWwgPSAnY2xpcF92ZXJ0aWNhbCcsXG4gICAgZmlsbCA9ICdmaWxsJyxcbiAgICBmaWxsX2hvcml6b250YWwgPSAnZmlsbF9ob3Jpem9udGFsJyxcbiAgICBmaWxsX3ZlcnRpY2FsID0gJ2ZpbGxfdmVydGljYWwnLFxuICAgIGxlZnQgPSAnbGVmdCcsXG4gICAgcmlnaHQgPSAncmlnaHQnLFxuICAgIHRvcCA9ICd0b3AnXG4gIH1cbiAgZXhwb3J0IGVudW0gRm9yZWdyb3VuZFRpbnRNb2RlRW51bSB7XG4gICAgYWRkID0gJ2FkZCcsXG4gICAgbXVsdGlwbHkgPSAnbXVsdGlwbHknLFxuICAgIHNjcmVlbiA9ICdzY3JlZW4nLFxuICAgIHNyY19hdG9wID0gJ3NyY19hdG9wJyxcbiAgICBzcmNfaW4gPSAnc3JjX2luJyxcbiAgICBzcmNfb3ZlciA9ICdzcmNfb3ZlcidcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEZyYW1lTGF5b3V0QXR0cmlidXRlcyBleHRlbmRzIFZpZXdHcm91cEF0dHJpYnV0ZXMge1xuICAgIG1lYXN1cmVBbGxDaGlsZHJlbj86IGJvb2xlYW47XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBGcmFtZUxheW91dExheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBNYXJnaW5MYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgICBsYXlvdXRfZ3Jhdml0eT86IExheW91dEdyYXZpdHlGbGFnc0VudW1bXTtcbiAgfVxuICBleHBvcnQgZW51bSBHcmF2aXR5RmxhZ3NFbnVtIHtcbiAgICBib3R0b20gPSAnYm90dG9tJyxcbiAgICBjZW50ZXIgPSAnY2VudGVyJyxcbiAgICBjZW50ZXJfaG9yaXpvbnRhbCA9ICdjZW50ZXJfaG9yaXpvbnRhbCcsXG4gICAgY2VudGVyX3ZlcnRpY2FsID0gJ2NlbnRlcl92ZXJ0aWNhbCcsXG4gICAgY2xpcF9ob3Jpem9udGFsID0gJ2NsaXBfaG9yaXpvbnRhbCcsXG4gICAgY2xpcF92ZXJ0aWNhbCA9ICdjbGlwX3ZlcnRpY2FsJyxcbiAgICBlbmQgPSAnZW5kJyxcbiAgICBmaWxsID0gJ2ZpbGwnLFxuICAgIGZpbGxfaG9yaXpvbnRhbCA9ICdmaWxsX2hvcml6b250YWwnLFxuICAgIGZpbGxfdmVydGljYWwgPSAnZmlsbF92ZXJ0aWNhbCcsXG4gICAgbGVmdCA9ICdsZWZ0JyxcbiAgICByaWdodCA9ICdyaWdodCcsXG4gICAgc3RhcnQgPSAnc3RhcnQnLFxuICAgIHRvcCA9ICd0b3AnXG4gIH1cbiAgZXhwb3J0IGVudW0gR3Jhdml0eUZsYWdzRW51bV8ge1xuICAgIGJvdHRvbSA9ICdib3R0b20nLFxuICAgIGNlbnRlciA9ICdjZW50ZXInLFxuICAgIGNlbnRlcl9ob3Jpem9udGFsID0gJ2NlbnRlcl9ob3Jpem9udGFsJyxcbiAgICBjZW50ZXJfdmVydGljYWwgPSAnY2VudGVyX3ZlcnRpY2FsJyxcbiAgICBjbGlwX2hvcml6b250YWwgPSAnY2xpcF9ob3Jpem9udGFsJyxcbiAgICBjbGlwX3ZlcnRpY2FsID0gJ2NsaXBfdmVydGljYWwnLFxuICAgIGVuZCA9ICdlbmQnLFxuICAgIGZpbGwgPSAnZmlsbCcsXG4gICAgZmlsbF9ob3Jpem9udGFsID0gJ2ZpbGxfaG9yaXpvbnRhbCcsXG4gICAgZmlsbF92ZXJ0aWNhbCA9ICdmaWxsX3ZlcnRpY2FsJyxcbiAgICBsZWZ0ID0gJ2xlZnQnLFxuICAgIHJpZ2h0ID0gJ3JpZ2h0JyxcbiAgICBzdGFydCA9ICdzdGFydCcsXG4gICAgdG9wID0gJ3RvcCdcbiAgfVxuICBleHBvcnQgZW51bSBHcmF2aXR5RmxhZ3NFbnVtX18ge1xuICAgIGJvdHRvbSA9ICdib3R0b20nLFxuICAgIGNlbnRlciA9ICdjZW50ZXInLFxuICAgIGNlbnRlcl9ob3Jpem9udGFsID0gJ2NlbnRlcl9ob3Jpem9udGFsJyxcbiAgICBjZW50ZXJfdmVydGljYWwgPSAnY2VudGVyX3ZlcnRpY2FsJyxcbiAgICBjbGlwX2hvcml6b250YWwgPSAnY2xpcF9ob3Jpem9udGFsJyxcbiAgICBjbGlwX3ZlcnRpY2FsID0gJ2NsaXBfdmVydGljYWwnLFxuICAgIGVuZCA9ICdlbmQnLFxuICAgIGZpbGwgPSAnZmlsbCcsXG4gICAgZmlsbF9ob3Jpem9udGFsID0gJ2ZpbGxfaG9yaXpvbnRhbCcsXG4gICAgZmlsbF92ZXJ0aWNhbCA9ICdmaWxsX3ZlcnRpY2FsJyxcbiAgICBsZWZ0ID0gJ2xlZnQnLFxuICAgIHJpZ2h0ID0gJ3JpZ2h0JyxcbiAgICBzdGFydCA9ICdzdGFydCcsXG4gICAgdG9wID0gJ3RvcCdcbiAgfVxuICBleHBvcnQgZW51bSBHcmF2aXR5RmxhZ3NFbnVtX19fIHtcbiAgICBib3R0b20gPSAnYm90dG9tJyxcbiAgICBjZW50ZXIgPSAnY2VudGVyJyxcbiAgICBjZW50ZXJfaG9yaXpvbnRhbCA9ICdjZW50ZXJfaG9yaXpvbnRhbCcsXG4gICAgY2VudGVyX3ZlcnRpY2FsID0gJ2NlbnRlcl92ZXJ0aWNhbCcsXG4gICAgY2xpcF9ob3Jpem9udGFsID0gJ2NsaXBfaG9yaXpvbnRhbCcsXG4gICAgY2xpcF92ZXJ0aWNhbCA9ICdjbGlwX3ZlcnRpY2FsJyxcbiAgICBlbmQgPSAnZW5kJyxcbiAgICBmaWxsID0gJ2ZpbGwnLFxuICAgIGZpbGxfaG9yaXpvbnRhbCA9ICdmaWxsX2hvcml6b250YWwnLFxuICAgIGZpbGxfdmVydGljYWwgPSAnZmlsbF92ZXJ0aWNhbCcsXG4gICAgbGVmdCA9ICdsZWZ0JyxcbiAgICByaWdodCA9ICdyaWdodCcsXG4gICAgc3RhcnQgPSAnc3RhcnQnLFxuICAgIHRvcCA9ICd0b3AnXG4gIH1cbiAgZXhwb3J0IGVudW0gR3Jhdml0eUZsYWdzRW51bV9fX18ge1xuICAgIGJvdHRvbSA9ICdib3R0b20nLFxuICAgIGNlbnRlciA9ICdjZW50ZXInLFxuICAgIGNlbnRlcl9ob3Jpem9udGFsID0gJ2NlbnRlcl9ob3Jpem9udGFsJyxcbiAgICBjZW50ZXJfdmVydGljYWwgPSAnY2VudGVyX3ZlcnRpY2FsJyxcbiAgICBjbGlwX2hvcml6b250YWwgPSAnY2xpcF9ob3Jpem9udGFsJyxcbiAgICBjbGlwX3ZlcnRpY2FsID0gJ2NsaXBfdmVydGljYWwnLFxuICAgIGVuZCA9ICdlbmQnLFxuICAgIGZpbGwgPSAnZmlsbCcsXG4gICAgZmlsbF9ob3Jpem9udGFsID0gJ2ZpbGxfaG9yaXpvbnRhbCcsXG4gICAgZmlsbF92ZXJ0aWNhbCA9ICdmaWxsX3ZlcnRpY2FsJyxcbiAgICBsZWZ0ID0gJ2xlZnQnLFxuICAgIHJpZ2h0ID0gJ3JpZ2h0JyxcbiAgICBzdGFydCA9ICdzdGFydCcsXG4gICAgdG9wID0gJ3RvcCdcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEdyaWRMYXlvdXRBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0dyb3VwQXR0cmlidXRlcyB7XG4gICAgYWxpZ25tZW50TW9kZT86IEFsaWdubWVudE1vZGVFbnVtO1xuICAgIGNvbHVtbkNvdW50PzogbnVtYmVyO1xuICAgIGNvbHVtbk9yZGVyUHJlc2VydmVkPzogYm9vbGVhbjtcbiAgICBvcmllbnRhdGlvbj86IE9yaWVudGF0aW9uRW51bV87XG4gICAgcm93Q291bnQ/OiBudW1iZXI7XG4gICAgcm93T3JkZXJQcmVzZXJ2ZWQ/OiBib29sZWFuO1xuICAgIHVzZURlZmF1bHRNYXJnaW5zPzogYm9vbGVhbjtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEdyaWRMYXlvdXRMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0dyb3VwTWFyZ2luTGF5b3V0UGFyYW1zQXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBHcmlkVmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyBBYnNMaXN0Vmlld0F0dHJpYnV0ZXMge1xuICAgIGNvbHVtbldpZHRoPzogc3RyaW5nO1xuICAgIGdyYXZpdHk/OiBudW1iZXIgfCBHcmF2aXR5RmxhZ3NFbnVtX19fX1tdO1xuICAgIGhvcml6b250YWxTcGFjaW5nPzogc3RyaW5nO1xuICAgIG51bUNvbHVtbnM/OiBudW1iZXIgfCBOdW1Db2x1bW5zRW51bTtcbiAgICBzdHJldGNoTW9kZT86IFN0cmV0Y2hNb2RlRW51bTtcbiAgICB2ZXJ0aWNhbFNwYWNpbmc/OiBzdHJpbmc7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBIb3Jpem9udGFsU2Nyb2xsVmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyBGcmFtZUxheW91dEF0dHJpYnV0ZXMge1xuICAgIGZpbGxWaWV3cG9ydD86IGJvb2xlYW47XG4gIH1cbiAgZXhwb3J0IGVudW0gSHlwaGVuYXRpb25GcmVxdWVuY3lFbnVtIHtcbiAgICBmdWxsID0gJ2Z1bGwnLFxuICAgIG5vbmUgPSAnbm9uZScsXG4gICAgbm9ybWFsID0gJ25vcm1hbCdcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEltYWdlQnV0dG9uQXR0cmlidXRlcyBleHRlbmRzIEltYWdlVmlld0F0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgSW1hZ2VTd2l0Y2hlckF0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3U3dpdGNoZXJBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEltYWdlVmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3QXR0cmlidXRlcyB7XG4gICAgYWRqdXN0Vmlld0JvdW5kcz86IGJvb2xlYW47XG4gICAgYmFzZWxpbmU/OiBzdHJpbmc7XG4gICAgYmFzZWxpbmVBbGlnbkJvdHRvbT86IGJvb2xlYW47XG4gICAgY3JvcFRvUGFkZGluZz86IGJvb2xlYW47XG4gICAgZHJhd2FibGVBbHBoYT86IG51bWJlcjtcbiAgICBtYXhIZWlnaHQ/OiBzdHJpbmc7XG4gICAgbWF4V2lkdGg/OiBzdHJpbmc7XG4gICAgc2NhbGVUeXBlPzogU2NhbGVUeXBlRW51bTtcbiAgICBzcmM/OiBzdHJpbmc7XG4gICAgdGludD86IHN0cmluZztcbiAgICB0aW50TW9kZT86IG51bWJlcjtcbiAgfVxuICBleHBvcnQgZW51bSBJbWVPcHRpb25zRmxhZ3NFbnVtIHtcbiAgICBhY3Rpb25Eb25lID0gJ2FjdGlvbkRvbmUnLFxuICAgIGFjdGlvbkdvID0gJ2FjdGlvbkdvJyxcbiAgICBhY3Rpb25OZXh0ID0gJ2FjdGlvbk5leHQnLFxuICAgIGFjdGlvbk5vbmUgPSAnYWN0aW9uTm9uZScsXG4gICAgYWN0aW9uUHJldmlvdXMgPSAnYWN0aW9uUHJldmlvdXMnLFxuICAgIGFjdGlvblNlYXJjaCA9ICdhY3Rpb25TZWFyY2gnLFxuICAgIGFjdGlvblNlbmQgPSAnYWN0aW9uU2VuZCcsXG4gICAgYWN0aW9uVW5zcGVjaWZpZWQgPSAnYWN0aW9uVW5zcGVjaWZpZWQnLFxuICAgIGZsYWdOYXZpZ2F0ZU5leHQgPSAnZmxhZ05hdmlnYXRlTmV4dCcsXG4gICAgZmxhZ05hdmlnYXRlUHJldmlvdXMgPSAnZmxhZ05hdmlnYXRlUHJldmlvdXMnLFxuICAgIGZsYWdOb0FjY2Vzc29yeUFjdGlvbiA9ICdmbGFnTm9BY2Nlc3NvcnlBY3Rpb24nLFxuICAgIGZsYWdOb0VudGVyQWN0aW9uID0gJ2ZsYWdOb0VudGVyQWN0aW9uJyxcbiAgICBmbGFnTm9FeHRyYWN0VWkgPSAnZmxhZ05vRXh0cmFjdFVpJyxcbiAgICBmbGFnTm9GdWxsc2NyZWVuID0gJ2ZsYWdOb0Z1bGxzY3JlZW4nLFxuICAgIGZsYWdOb1BlcnNvbmFsaXplZExlYXJuaW5nID0gJ2ZsYWdOb1BlcnNvbmFsaXplZExlYXJuaW5nJyxcbiAgICBub3JtYWwgPSAnbm9ybWFsJ1xuICB9XG4gIGV4cG9ydCBlbnVtIEltcG9ydGFudEZvckFjY2Vzc2liaWxpdHlFbnVtIHtcbiAgICBhdXRvID0gJ2F1dG8nLFxuICAgIG5vID0gJ25vJyxcbiAgICBub0hpZGVEZXNjZW5kYW50cyA9ICdub0hpZGVEZXNjZW5kYW50cycsXG4gICAgeWVzID0gJ3llcydcbiAgfVxuICBleHBvcnQgZW51bSBJbXBvcnRhbnRGb3JBdXRvZmlsbEZsYWdzRW51bSB7XG4gICAgYXV0byA9ICdhdXRvJyxcbiAgICBubyA9ICdubycsXG4gICAgbm9FeGNsdWRlRGVzY2VuZGFudHMgPSAnbm9FeGNsdWRlRGVzY2VuZGFudHMnLFxuICAgIHllcyA9ICd5ZXMnLFxuICAgIHllc0V4Y2x1ZGVEZXNjZW5kYW50cyA9ICd5ZXNFeGNsdWRlRGVzY2VuZGFudHMnXG4gIH1cbiAgZXhwb3J0IGVudW0gSW5kZXRlcm1pbmF0ZVRpbnRNb2RlRW51bSB7XG4gICAgYWRkID0gJ2FkZCcsXG4gICAgbXVsdGlwbHkgPSAnbXVsdGlwbHknLFxuICAgIHNjcmVlbiA9ICdzY3JlZW4nLFxuICAgIHNyY19hdG9wID0gJ3NyY19hdG9wJyxcbiAgICBzcmNfaW4gPSAnc3JjX2luJyxcbiAgICBzcmNfb3ZlciA9ICdzcmNfb3ZlcidcbiAgfVxuICBleHBvcnQgZW51bSBJbnB1dFR5cGVGbGFnc0VudW0ge1xuICAgIGRhdGUgPSAnZGF0ZScsXG4gICAgZGF0ZXRpbWUgPSAnZGF0ZXRpbWUnLFxuICAgIG5vbmUgPSAnbm9uZScsXG4gICAgbnVtYmVyID0gJ251bWJlcicsXG4gICAgbnVtYmVyRGVjaW1hbCA9ICdudW1iZXJEZWNpbWFsJyxcbiAgICBudW1iZXJQYXNzd29yZCA9ICdudW1iZXJQYXNzd29yZCcsXG4gICAgbnVtYmVyU2lnbmVkID0gJ251bWJlclNpZ25lZCcsXG4gICAgcGhvbmUgPSAncGhvbmUnLFxuICAgIHRleHQgPSAndGV4dCcsXG4gICAgdGV4dEF1dG9Db21wbGV0ZSA9ICd0ZXh0QXV0b0NvbXBsZXRlJyxcbiAgICB0ZXh0QXV0b0NvcnJlY3QgPSAndGV4dEF1dG9Db3JyZWN0JyxcbiAgICB0ZXh0Q2FwQ2hhcmFjdGVycyA9ICd0ZXh0Q2FwQ2hhcmFjdGVycycsXG4gICAgdGV4dENhcFNlbnRlbmNlcyA9ICd0ZXh0Q2FwU2VudGVuY2VzJyxcbiAgICB0ZXh0Q2FwV29yZHMgPSAndGV4dENhcFdvcmRzJyxcbiAgICB0ZXh0RW1haWxBZGRyZXNzID0gJ3RleHRFbWFpbEFkZHJlc3MnLFxuICAgIHRleHRFbWFpbFN1YmplY3QgPSAndGV4dEVtYWlsU3ViamVjdCcsXG4gICAgdGV4dEZpbHRlciA9ICd0ZXh0RmlsdGVyJyxcbiAgICB0ZXh0SW1lTXVsdGlMaW5lID0gJ3RleHRJbWVNdWx0aUxpbmUnLFxuICAgIHRleHRMb25nTWVzc2FnZSA9ICd0ZXh0TG9uZ01lc3NhZ2UnLFxuICAgIHRleHRNdWx0aUxpbmUgPSAndGV4dE11bHRpTGluZScsXG4gICAgdGV4dE5vU3VnZ2VzdGlvbnMgPSAndGV4dE5vU3VnZ2VzdGlvbnMnLFxuICAgIHRleHRQYXNzd29yZCA9ICd0ZXh0UGFzc3dvcmQnLFxuICAgIHRleHRQZXJzb25OYW1lID0gJ3RleHRQZXJzb25OYW1lJyxcbiAgICB0ZXh0UGhvbmV0aWMgPSAndGV4dFBob25ldGljJyxcbiAgICB0ZXh0UG9zdGFsQWRkcmVzcyA9ICd0ZXh0UG9zdGFsQWRkcmVzcycsXG4gICAgdGV4dFNob3J0TWVzc2FnZSA9ICd0ZXh0U2hvcnRNZXNzYWdlJyxcbiAgICB0ZXh0VXJpID0gJ3RleHRVcmknLFxuICAgIHRleHRWaXNpYmxlUGFzc3dvcmQgPSAndGV4dFZpc2libGVQYXNzd29yZCcsXG4gICAgdGV4dFdlYkVkaXRUZXh0ID0gJ3RleHRXZWJFZGl0VGV4dCcsXG4gICAgdGV4dFdlYkVtYWlsQWRkcmVzcyA9ICd0ZXh0V2ViRW1haWxBZGRyZXNzJyxcbiAgICB0ZXh0V2ViUGFzc3dvcmQgPSAndGV4dFdlYlBhc3N3b3JkJyxcbiAgICB0aW1lID0gJ3RpbWUnXG4gIH1cbiAgZXhwb3J0IGVudW0gSnVzdGlmaWNhdGlvbk1vZGVFbnVtIHtcbiAgICBpbnRlcl93b3JkID0gJ2ludGVyX3dvcmQnLFxuICAgIG5vbmUgPSAnbm9uZSdcbiAgfVxuICBleHBvcnQgZW51bSBMYXllclR5cGVFbnVtIHtcbiAgICBoYXJkd2FyZSA9ICdoYXJkd2FyZScsXG4gICAgbm9uZSA9ICdub25lJyxcbiAgICBzb2Z0d2FyZSA9ICdzb2Z0d2FyZSdcbiAgfVxuICBleHBvcnQgZW51bSBMYXlvdXREaXJlY3Rpb25FbnVtIHtcbiAgICBpbmhlcml0ID0gJ2luaGVyaXQnLFxuICAgIGxvY2FsZSA9ICdsb2NhbGUnLFxuICAgIGx0ciA9ICdsdHInLFxuICAgIHJ0bCA9ICdydGwnXG4gIH1cbiAgZXhwb3J0IGVudW0gTGF5b3V0R3Jhdml0eUZsYWdzRW51bSB7XG4gICAgYm90dG9tID0gJ2JvdHRvbScsXG4gICAgY2VudGVyID0gJ2NlbnRlcicsXG4gICAgY2VudGVyX2hvcml6b250YWwgPSAnY2VudGVyX2hvcml6b250YWwnLFxuICAgIGNlbnRlcl92ZXJ0aWNhbCA9ICdjZW50ZXJfdmVydGljYWwnLFxuICAgIGNsaXBfaG9yaXpvbnRhbCA9ICdjbGlwX2hvcml6b250YWwnLFxuICAgIGNsaXBfdmVydGljYWwgPSAnY2xpcF92ZXJ0aWNhbCcsXG4gICAgZW5kID0gJ2VuZCcsXG4gICAgZmlsbCA9ICdmaWxsJyxcbiAgICBmaWxsX2hvcml6b250YWwgPSAnZmlsbF9ob3Jpem9udGFsJyxcbiAgICBmaWxsX3ZlcnRpY2FsID0gJ2ZpbGxfdmVydGljYWwnLFxuICAgIGxlZnQgPSAnbGVmdCcsXG4gICAgcmlnaHQgPSAncmlnaHQnLFxuICAgIHN0YXJ0ID0gJ3N0YXJ0JyxcbiAgICB0b3AgPSAndG9wJ1xuICB9XG4gIGV4cG9ydCBlbnVtIExheW91dEdyYXZpdHlGbGFnc0VudW1fIHtcbiAgICBib3R0b20gPSAnYm90dG9tJyxcbiAgICBjZW50ZXIgPSAnY2VudGVyJyxcbiAgICBjZW50ZXJfaG9yaXpvbnRhbCA9ICdjZW50ZXJfaG9yaXpvbnRhbCcsXG4gICAgY2VudGVyX3ZlcnRpY2FsID0gJ2NlbnRlcl92ZXJ0aWNhbCcsXG4gICAgY2xpcF9ob3Jpem9udGFsID0gJ2NsaXBfaG9yaXpvbnRhbCcsXG4gICAgY2xpcF92ZXJ0aWNhbCA9ICdjbGlwX3ZlcnRpY2FsJyxcbiAgICBlbmQgPSAnZW5kJyxcbiAgICBmaWxsID0gJ2ZpbGwnLFxuICAgIGZpbGxfaG9yaXpvbnRhbCA9ICdmaWxsX2hvcml6b250YWwnLFxuICAgIGZpbGxfdmVydGljYWwgPSAnZmlsbF92ZXJ0aWNhbCcsXG4gICAgbGVmdCA9ICdsZWZ0JyxcbiAgICByaWdodCA9ICdyaWdodCcsXG4gICAgc3RhcnQgPSAnc3RhcnQnLFxuICAgIHRvcCA9ICd0b3AnXG4gIH1cbiAgZXhwb3J0IGVudW0gTGF5b3V0SGVpZ2h0RW51bSB7XG4gICAgZmlsbF9wYXJlbnQgPSAnZmlsbF9wYXJlbnQnLFxuICAgIG1hdGNoX3BhcmVudCA9ICdtYXRjaF9wYXJlbnQnLFxuICAgIHdyYXBfY29udGVudCA9ICd3cmFwX2NvbnRlbnQnXG4gIH1cbiAgZXhwb3J0IGVudW0gTGF5b3V0TW9kZUVudW0ge1xuICAgIGNsaXBCb3VuZHMgPSAnY2xpcEJvdW5kcycsXG4gICAgb3B0aWNhbEJvdW5kcyA9ICdvcHRpY2FsQm91bmRzJ1xuICB9XG4gIGV4cG9ydCBlbnVtIExheW91dFdpZHRoRW51bSB7XG4gICAgZmlsbF9wYXJlbnQgPSAnZmlsbF9wYXJlbnQnLFxuICAgIG1hdGNoX3BhcmVudCA9ICdtYXRjaF9wYXJlbnQnLFxuICAgIHdyYXBfY29udGVudCA9ICd3cmFwX2NvbnRlbnQnXG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBMaW5lYXJMYXlvdXRBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0dyb3VwQXR0cmlidXRlcyB7XG4gICAgYmFzZWxpbmVBbGlnbmVkPzogYm9vbGVhbjtcbiAgICBiYXNlbGluZUFsaWduZWRDaGlsZEluZGV4PzogbnVtYmVyO1xuICAgIGRpdmlkZXI/OiBudW1iZXI7XG4gICAgZGl2aWRlclBhZGRpbmc/OiBzdHJpbmc7XG4gICAgZ3Jhdml0eT86IEdyYXZpdHlGbGFnc0VudW1fX1tdO1xuICAgIG1lYXN1cmVXaXRoTGFyZ2VzdENoaWxkPzogYm9vbGVhbjtcbiAgICBvcmllbnRhdGlvbj86IE9yaWVudGF0aW9uRW51bTtcbiAgICBzaG93RGl2aWRlcnM/OiBTaG93RGl2aWRlcnNGbGFnc0VudW1bXTtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIExpbmVhckxheW91dExheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBNYXJnaW5MYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgICBsYXlvdXRfZ3Jhdml0eT86IG51bWJlciB8IExheW91dEdyYXZpdHlGbGFnc0VudW1fW107XG4gICAgbGF5b3V0X3dlaWdodD86IG51bWJlcjtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIExpc3RWaWV3QXR0cmlidXRlcyBleHRlbmRzIEFic0xpc3RWaWV3QXR0cmlidXRlcyB7XG4gICAgZGl2aWRlcj86IHN0cmluZztcbiAgICBkaXZpZGVySGVpZ2h0Pzogc3RyaW5nO1xuICAgIGZvb3RlckRpdmlkZXJzRW5hYmxlZD86IGJvb2xlYW47XG4gICAgaGVhZGVyRGl2aWRlcnNFbmFibGVkPzogYm9vbGVhbjtcbiAgICBvdmVyU2Nyb2xsRm9vdGVyPzogc3RyaW5nO1xuICAgIG92ZXJTY3JvbGxIZWFkZXI/OiBzdHJpbmc7XG4gIH1cbiAgZXhwb3J0IGVudW0gTWFycXVlZVJlcGVhdExpbWl0RW51bSB7XG4gICAgbWFycXVlZV9mb3JldmVyID0gJ21hcnF1ZWVfZm9yZXZlcidcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIE1lZGlhQ29udHJvbGxlckF0dHJpYnV0ZXMgZXh0ZW5kcyBGcmFtZUxheW91dEF0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgTXVsdGlBdXRvQ29tcGxldGVUZXh0Vmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyBBdXRvQ29tcGxldGVUZXh0Vmlld0F0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBlbnVtIE51bUNvbHVtbnNFbnVtIHtcbiAgICBhdXRvX2ZpdCA9ICdhdXRvX2ZpdCdcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIE51bWJlclBpY2tlckF0dHJpYnV0ZXMgZXh0ZW5kcyBMaW5lYXJMYXlvdXRBdHRyaWJ1dGVzIHtcbiAgICBpbnRlcm5hbE1pbkhlaWdodD86IHN0cmluZztcbiAgICBpbnRlcm5hbE1pbldpZHRoPzogc3RyaW5nO1xuICB9XG4gIGV4cG9ydCBlbnVtIE9yaWVudGF0aW9uRW51bSB7XG4gICAgaG9yaXpvbnRhbCA9ICdob3Jpem9udGFsJyxcbiAgICB2ZXJ0aWNhbCA9ICd2ZXJ0aWNhbCdcbiAgfVxuICBleHBvcnQgZW51bSBPcmllbnRhdGlvbkVudW1fIHtcbiAgICBob3Jpem9udGFsID0gJ2hvcml6b250YWwnLFxuICAgIHZlcnRpY2FsID0gJ3ZlcnRpY2FsJ1xuICB9XG4gIGV4cG9ydCBlbnVtIE92ZXJTY3JvbGxNb2RlRW51bSB7XG4gICAgYWx3YXlzID0gJ2Fsd2F5cycsXG4gICAgaWZDb250ZW50U2Nyb2xscyA9ICdpZkNvbnRlbnRTY3JvbGxzJyxcbiAgICBuZXZlciA9ICduZXZlcidcbiAgfVxuICBleHBvcnQgZW51bSBQZXJzaXN0ZW50RHJhd2luZ0NhY2hlRmxhZ3NFbnVtIHtcbiAgICBhbGwgPSAnYWxsJyxcbiAgICBhbmltYXRpb24gPSAnYW5pbWF0aW9uJyxcbiAgICBub25lID0gJ25vbmUnLFxuICAgIHNjcm9sbGluZyA9ICdzY3JvbGxpbmcnXG4gIH1cbiAgZXhwb3J0IGVudW0gUHJvZ3Jlc3NCYWNrZ3JvdW5kVGludE1vZGVFbnVtIHtcbiAgICBhZGQgPSAnYWRkJyxcbiAgICBtdWx0aXBseSA9ICdtdWx0aXBseScsXG4gICAgc2NyZWVuID0gJ3NjcmVlbicsXG4gICAgc3JjX2F0b3AgPSAnc3JjX2F0b3AnLFxuICAgIHNyY19pbiA9ICdzcmNfaW4nLFxuICAgIHNyY19vdmVyID0gJ3NyY19vdmVyJ1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgUHJvZ3Jlc3NCYXJBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0F0dHJpYnV0ZXMge1xuICAgIGluZGV0ZXJtaW5hdGU/OiBib29sZWFuO1xuICAgIGluZGV0ZXJtaW5hdGVEcmF3YWJsZT86IHN0cmluZztcbiAgICBpbmRldGVybWluYXRlVGludD86IHN0cmluZztcbiAgICBpbmRldGVybWluYXRlVGludE1vZGU/OiBJbmRldGVybWluYXRlVGludE1vZGVFbnVtO1xuICAgIGludGVycG9sYXRvcj86IHN0cmluZztcbiAgICBtYXg/OiBudW1iZXI7XG4gICAgbWluPzogbnVtYmVyO1xuICAgIHByb2dyZXNzPzogbnVtYmVyO1xuICAgIHByb2dyZXNzQmFja2dyb3VuZFRpbnQ/OiBzdHJpbmc7XG4gICAgcHJvZ3Jlc3NCYWNrZ3JvdW5kVGludE1vZGU/OiBQcm9ncmVzc0JhY2tncm91bmRUaW50TW9kZUVudW07XG4gICAgcHJvZ3Jlc3NEcmF3YWJsZT86IHN0cmluZztcbiAgICBwcm9ncmVzc1RpbnQ/OiBzdHJpbmc7XG4gICAgcHJvZ3Jlc3NUaW50TW9kZT86IFByb2dyZXNzVGludE1vZGVFbnVtO1xuICAgIHNlY29uZGFyeVByb2dyZXNzPzogbnVtYmVyO1xuICAgIHNlY29uZGFyeVByb2dyZXNzVGludD86IHN0cmluZztcbiAgICBzZWNvbmRhcnlQcm9ncmVzc1RpbnRNb2RlPzogU2Vjb25kYXJ5UHJvZ3Jlc3NUaW50TW9kZUVudW07XG4gIH1cbiAgZXhwb3J0IGVudW0gUHJvZ3Jlc3NUaW50TW9kZUVudW0ge1xuICAgIGFkZCA9ICdhZGQnLFxuICAgIG11bHRpcGx5ID0gJ211bHRpcGx5JyxcbiAgICBzY3JlZW4gPSAnc2NyZWVuJyxcbiAgICBzcmNfYXRvcCA9ICdzcmNfYXRvcCcsXG4gICAgc3JjX2luID0gJ3NyY19pbicsXG4gICAgc3JjX292ZXIgPSAnc3JjX292ZXInXG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBRdWlja0NvbnRhY3RCYWRnZUF0dHJpYnV0ZXMgZXh0ZW5kcyBJbWFnZVZpZXdBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFJhZGlvQnV0dG9uQXR0cmlidXRlcyBleHRlbmRzIENvbXBvdW5kQnV0dG9uQXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBSYWRpb0dyb3VwQXR0cmlidXRlcyBleHRlbmRzIExpbmVhckxheW91dEF0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgUmFkaW9Hcm91cExheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBMaW5lYXJMYXlvdXRMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFJhdGluZ0JhckF0dHJpYnV0ZXMgZXh0ZW5kcyBBYnNTZWVrQmFyQXR0cmlidXRlcyB7XG4gICAgaXNJbmRpY2F0b3I/OiBib29sZWFuO1xuICAgIG51bVN0YXJzPzogbnVtYmVyO1xuICAgIHJhdGluZz86IG51bWJlcjtcbiAgICBzdGVwU2l6ZT86IG51bWJlcjtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFJlbGF0aXZlTGF5b3V0QXR0cmlidXRlcyBleHRlbmRzIFZpZXdHcm91cEF0dHJpYnV0ZXMge1xuICAgIGdyYXZpdHk/OiBHcmF2aXR5RmxhZ3NFbnVtX1tdO1xuICAgIGlnbm9yZUdyYXZpdHk/OiBzdHJpbmc7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBSZWxhdGl2ZUxheW91dExheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBNYXJnaW5MYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgZW51bSBTY2FsZVR5cGVFbnVtIHtcbiAgICBjZW50ZXIgPSAnY2VudGVyJyxcbiAgICBjZW50ZXJDcm9wID0gJ2NlbnRlckNyb3AnLFxuICAgIGNlbnRlckluc2lkZSA9ICdjZW50ZXJJbnNpZGUnLFxuICAgIGZpdENlbnRlciA9ICdmaXRDZW50ZXInLFxuICAgIGZpdEVuZCA9ICdmaXRFbmQnLFxuICAgIGZpdFN0YXJ0ID0gJ2ZpdFN0YXJ0JyxcbiAgICBmaXRYWSA9ICdmaXRYWScsXG4gICAgbWF0cml4ID0gJ21hdHJpeCdcbiAgfVxuICBleHBvcnQgZW51bSBTY3JvbGxJbmRpY2F0b3JzRmxhZ3NFbnVtIHtcbiAgICBib3R0b20gPSAnYm90dG9tJyxcbiAgICBlbmQgPSAnZW5kJyxcbiAgICBsZWZ0ID0gJ2xlZnQnLFxuICAgIG5vbmUgPSAnbm9uZScsXG4gICAgcmlnaHQgPSAncmlnaHQnLFxuICAgIHN0YXJ0ID0gJ3N0YXJ0JyxcbiAgICB0b3AgPSAndG9wJ1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgU2Nyb2xsVmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyBGcmFtZUxheW91dEF0dHJpYnV0ZXMge1xuICAgIGZpbGxWaWV3cG9ydD86IGJvb2xlYW47XG4gIH1cbiAgZXhwb3J0IGVudW0gU2Nyb2xsYmFyU3R5bGVFbnVtIHtcbiAgICBpbnNpZGVJbnNldCA9ICdpbnNpZGVJbnNldCcsXG4gICAgaW5zaWRlT3ZlcmxheSA9ICdpbnNpZGVPdmVybGF5JyxcbiAgICBvdXRzaWRlSW5zZXQgPSAnb3V0c2lkZUluc2V0JyxcbiAgICBvdXRzaWRlT3ZlcmxheSA9ICdvdXRzaWRlT3ZlcmxheSdcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFNlYXJjaFZpZXdBdHRyaWJ1dGVzIGV4dGVuZHMgTGluZWFyTGF5b3V0QXR0cmlidXRlcyB7XG4gICAgaWNvbmlmaWVkQnlEZWZhdWx0PzogYm9vbGVhbjtcbiAgICBpbWVPcHRpb25zPzogbnVtYmVyO1xuICAgIGlucHV0VHlwZT86IG51bWJlcjtcbiAgICBtYXhXaWR0aD86IHN0cmluZztcbiAgICBxdWVyeUhpbnQ/OiBzdHJpbmc7XG4gIH1cbiAgZXhwb3J0IGVudW0gU2Vjb25kYXJ5UHJvZ3Jlc3NUaW50TW9kZUVudW0ge1xuICAgIGFkZCA9ICdhZGQnLFxuICAgIG11bHRpcGx5ID0gJ211bHRpcGx5JyxcbiAgICBzY3JlZW4gPSAnc2NyZWVuJyxcbiAgICBzcmNfYXRvcCA9ICdzcmNfYXRvcCcsXG4gICAgc3JjX2luID0gJ3NyY19pbicsXG4gICAgc3JjX292ZXIgPSAnc3JjX292ZXInXG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBTZWVrQmFyQXR0cmlidXRlcyBleHRlbmRzIEFic1NlZWtCYXJBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgZW51bSBTaG93RGl2aWRlcnNGbGFnc0VudW0ge1xuICAgIGJlZ2lubmluZyA9ICdiZWdpbm5pbmcnLFxuICAgIGVuZCA9ICdlbmQnLFxuICAgIG1pZGRsZSA9ICdtaWRkbGUnLFxuICAgIG5vbmUgPSAnbm9uZSdcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFNwYWNlQXR0cmlidXRlcyBleHRlbmRzIFZpZXdBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFNwaW5uZXJBdHRyaWJ1dGVzIGV4dGVuZHMgQWJzU3Bpbm5lckF0dHJpYnV0ZXMge1xuICAgIGRyb3BEb3duV2lkdGg/OiBudW1iZXI7XG4gICAgZ3Jhdml0eT86IEdyYXZpdHlGbGFnc0VudW1fX19bXTtcbiAgICBwb3B1cEJhY2tncm91bmQ/OiBudW1iZXI7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBTdGFja1ZpZXdBdHRyaWJ1dGVzIGV4dGVuZHMgQWRhcHRlclZpZXdBbmltYXRvckF0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBlbnVtIFN0cmV0Y2hNb2RlRW51bSB7XG4gICAgY29sdW1uV2lkdGggPSAnY29sdW1uV2lkdGgnLFxuICAgIG5vbmUgPSAnbm9uZScsXG4gICAgc3BhY2luZ1dpZHRoID0gJ3NwYWNpbmdXaWR0aCcsXG4gICAgc3BhY2luZ1dpZHRoVW5pZm9ybSA9ICdzcGFjaW5nV2lkdGhVbmlmb3JtJ1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgU3VyZmFjZVZpZXdBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0F0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgU3dpdGNoQXR0cmlidXRlcyBleHRlbmRzIENvbXBvdW5kQnV0dG9uQXR0cmlidXRlcyB7XG4gICAgc2hvd1RleHQ/OiBib29sZWFuO1xuICAgIHNwbGl0VHJhY2s/OiBib29sZWFuO1xuICAgIHN3aXRjaE1pbldpZHRoPzogc3RyaW5nO1xuICAgIHN3aXRjaFBhZGRpbmc/OiBzdHJpbmc7XG4gICAgc3dpdGNoVGV4dEFwcGVhcmFuY2U/OiBzdHJpbmc7XG4gICAgdGV4dE9mZj86IHN0cmluZztcbiAgICB0ZXh0T24/OiBzdHJpbmc7XG4gICAgdGh1bWI/OiBudW1iZXI7XG4gICAgdGh1bWJUZXh0UGFkZGluZz86IHN0cmluZztcbiAgICB0aHVtYlRpbnQ/OiBudW1iZXI7XG4gICAgdGh1bWJUaW50TW9kZT86IG51bWJlcjtcbiAgICB0cmFjaz86IHN0cmluZztcbiAgICB0cmFja1RpbnQ/OiBzdHJpbmc7XG4gICAgdHJhY2tUaW50TW9kZT86IFRyYWNrVGludE1vZGVFbnVtO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVGFiSG9zdEF0dHJpYnV0ZXMgZXh0ZW5kcyBGcmFtZUxheW91dEF0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVGFiV2lkZ2V0QXR0cmlidXRlcyBleHRlbmRzIExpbmVhckxheW91dEF0dHJpYnV0ZXMge1xuICAgIHRhYlN0cmlwRW5hYmxlZD86IGJvb2xlYW47XG4gICAgdGFiU3RyaXBMZWZ0Pzogc3RyaW5nO1xuICAgIHRhYlN0cmlwUmlnaHQ/OiBzdHJpbmc7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBUYWJsZUxheW91dEF0dHJpYnV0ZXMgZXh0ZW5kcyBMaW5lYXJMYXlvdXRBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFRhYmxlTGF5b3V0TGF5b3V0UGFyYW1zQXR0cmlidXRlcyBleHRlbmRzIExpbmVhckxheW91dExheW91dFBhcmFtc0F0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVGFibGVSb3dBdHRyaWJ1dGVzIGV4dGVuZHMgTGluZWFyTGF5b3V0QXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBUYWJsZVJvd0xheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBMaW5lYXJMYXlvdXRMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgICBUYWJsZVJvd19DZWxsX2xheW91dF9jb2x1bW4/OiBudW1iZXI7XG4gICAgVGFibGVSb3dfQ2VsbF9sYXlvdXRfc3Bhbj86IG51bWJlcjtcbiAgfVxuICBleHBvcnQgZW51bSBUZXh0QWxpZ25tZW50RW51bSB7XG4gICAgY2VudGVyID0gJ2NlbnRlcicsXG4gICAgZ3Jhdml0eSA9ICdncmF2aXR5JyxcbiAgICBpbmhlcml0ID0gJ2luaGVyaXQnLFxuICAgIHRleHRFbmQgPSAndGV4dEVuZCcsXG4gICAgdGV4dFN0YXJ0ID0gJ3RleHRTdGFydCcsXG4gICAgdmlld0VuZCA9ICd2aWV3RW5kJyxcbiAgICB2aWV3U3RhcnQgPSAndmlld1N0YXJ0J1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVGV4dENsb2NrQXR0cmlidXRlcyBleHRlbmRzIFRleHRWaWV3QXR0cmlidXRlcyB7XG4gICAgZm9ybWF0MTJIb3VyPzogc3RyaW5nO1xuICAgIGZvcm1hdDI0SG91cj86IHN0cmluZztcbiAgICB0aW1lWm9uZT86IHN0cmluZztcbiAgfVxuICBleHBvcnQgZW51bSBUZXh0RGlyZWN0aW9uRW51bSB7XG4gICAgYW55UnRsID0gJ2FueVJ0bCcsXG4gICAgZmlyc3RTdHJvbmcgPSAnZmlyc3RTdHJvbmcnLFxuICAgIGZpcnN0U3Ryb25nTHRyID0gJ2ZpcnN0U3Ryb25nTHRyJyxcbiAgICBmaXJzdFN0cm9uZ1J0bCA9ICdmaXJzdFN0cm9uZ1J0bCcsXG4gICAgaW5oZXJpdCA9ICdpbmhlcml0JyxcbiAgICBsb2NhbGUgPSAnbG9jYWxlJyxcbiAgICBsdHIgPSAnbHRyJyxcbiAgICBydGwgPSAncnRsJ1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVGV4dFN3aXRjaGVyQXR0cmlidXRlcyBleHRlbmRzIFZpZXdTd2l0Y2hlckF0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVGV4dFZpZXdBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0F0dHJpYnV0ZXMge1xuICAgIFZpZXdfY2xpY2thYmxlPzogYm9vbGVhbjtcbiAgICBWaWV3X2xvbmdDbGlja2FibGU/OiBib29sZWFuO1xuICAgIGF1dG9MaW5rPzogQXV0b0xpbmtGbGFnc0VudW1bXTtcbiAgICBhdXRvU2l6ZVRleHRUeXBlPzogQXV0b1NpemVUZXh0VHlwZUVudW07XG4gICAgYnJlYWtTdHJhdGVneT86IEJyZWFrU3RyYXRlZ3lFbnVtO1xuICAgIGN1cnNvclZpc2libGU/OiBib29sZWFuO1xuICAgIGRyYXdhYmxlQm90dG9tPzogc3RyaW5nO1xuICAgIGRyYXdhYmxlTGVmdD86IHN0cmluZztcbiAgICBkcmF3YWJsZVBhZGRpbmc/OiBzdHJpbmc7XG4gICAgZHJhd2FibGVSaWdodD86IHN0cmluZztcbiAgICBkcmF3YWJsZVRpbnQ/OiBzdHJpbmc7XG4gICAgZHJhd2FibGVUaW50TW9kZT86IERyYXdhYmxlVGludE1vZGVFbnVtO1xuICAgIGRyYXdhYmxlVG9wPzogc3RyaW5nO1xuICAgIGVkaXRvckV4dHJhcz86IHN0cmluZztcbiAgICBlbGVnYW50VGV4dEhlaWdodD86IGJvb2xlYW47XG4gICAgZWxsaXBzaXplPzogRWxsaXBzaXplRW51bTtcbiAgICBlbXM/OiBudW1iZXI7XG4gICAgZW5hYmxlZD86IGJvb2xlYW47XG4gICAgZmFsbGJhY2tMaW5lU3BhY2luZz86IGJvb2xlYW47XG4gICAgZmlyc3RCYXNlbGluZVRvVG9wSGVpZ2h0Pzogc3RyaW5nO1xuICAgIGZvbnRGZWF0dXJlU2V0dGluZ3M/OiBzdHJpbmc7XG4gICAgZnJlZXplc1RleHQ/OiBib29sZWFuO1xuICAgIGdyYXZpdHk/OiBHcmF2aXR5RmxhZ3NFbnVtW107XG4gICAgaGVpZ2h0Pzogc3RyaW5nO1xuICAgIGhpbnQ/OiBzdHJpbmc7XG4gICAgaHlwaGVuYXRpb25GcmVxdWVuY3k/OiBIeXBoZW5hdGlvbkZyZXF1ZW5jeUVudW07XG4gICAgaW1lT3B0aW9ucz86IEltZU9wdGlvbnNGbGFnc0VudW1bXTtcbiAgICBpbmNsdWRlRm9udFBhZGRpbmc/OiBib29sZWFuO1xuICAgIGlucHV0VHlwZT86IElucHV0VHlwZUZsYWdzRW51bVtdO1xuICAgIGp1c3RpZmljYXRpb25Nb2RlPzogSnVzdGlmaWNhdGlvbk1vZGVFbnVtO1xuICAgIGxhc3RCYXNlbGluZVRvQm90dG9tSGVpZ2h0Pzogc3RyaW5nO1xuICAgIGxldHRlclNwYWNpbmc/OiBudW1iZXI7XG4gICAgbGluZUhlaWdodD86IHN0cmluZztcbiAgICBsaW5lcz86IG51bWJlcjtcbiAgICBsaW5rc0NsaWNrYWJsZT86IGJvb2xlYW47XG4gICAgbWFycXVlZVJlcGVhdExpbWl0PzogbnVtYmVyIHwgTWFycXVlZVJlcGVhdExpbWl0RW51bTtcbiAgICBtYXhFbXM/OiBudW1iZXI7XG4gICAgbWF4SGVpZ2h0Pzogc3RyaW5nO1xuICAgIG1heExpbmVzPzogbnVtYmVyO1xuICAgIG1heFdpZHRoPzogc3RyaW5nO1xuICAgIG1pbkVtcz86IG51bWJlcjtcbiAgICBtaW5MaW5lcz86IG51bWJlcjtcbiAgICBwcml2YXRlSW1lT3B0aW9ucz86IHN0cmluZztcbiAgICBzaGFkb3dDb2xvcj86IHN0cmluZztcbiAgICBzaGFkb3dEeD86IG51bWJlcjtcbiAgICBzaGFkb3dEeT86IG51bWJlcjtcbiAgICBzaGFkb3dSYWRpdXM/OiBudW1iZXI7XG4gICAgdGV4dD86IHN0cmluZztcbiAgICB0ZXh0QWxsQ2Fwcz86IGJvb2xlYW47XG4gICAgdGV4dENvbG9yPzogc3RyaW5nO1xuICAgIHRleHRDb2xvckhpZ2hsaWdodD86IHN0cmluZztcbiAgICB0ZXh0Q29sb3JIaW50Pzogc3RyaW5nO1xuICAgIHRleHRDb2xvckxpbms/OiBzdHJpbmc7XG4gICAgdGV4dElzU2VsZWN0YWJsZT86IGJvb2xlYW47XG4gICAgdGV4dFNjYWxlWD86IG51bWJlcjtcbiAgICB0ZXh0U2l6ZT86IHN0cmluZztcbiAgICB3aWR0aD86IHN0cmluZztcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFRleHR1cmVWaWV3QXR0cmlidXRlcyBleHRlbmRzIFZpZXdBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgZW51bSBUaHVtYlRpbnRNb2RlRW51bSB7XG4gICAgYWRkID0gJ2FkZCcsXG4gICAgbXVsdGlwbHkgPSAnbXVsdGlwbHknLFxuICAgIHNjcmVlbiA9ICdzY3JlZW4nLFxuICAgIHNyY19hdG9wID0gJ3NyY19hdG9wJyxcbiAgICBzcmNfaW4gPSAnc3JjX2luJyxcbiAgICBzcmNfb3ZlciA9ICdzcmNfb3ZlcidcbiAgfVxuICBleHBvcnQgZW51bSBUaWNrTWFya1RpbnRNb2RlRW51bSB7XG4gICAgYWRkID0gJ2FkZCcsXG4gICAgbXVsdGlwbHkgPSAnbXVsdGlwbHknLFxuICAgIHNjcmVlbiA9ICdzY3JlZW4nLFxuICAgIHNyY19hdG9wID0gJ3NyY19hdG9wJyxcbiAgICBzcmNfaW4gPSAnc3JjX2luJyxcbiAgICBzcmNfb3ZlciA9ICdzcmNfb3ZlcidcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFRpbWVQaWNrZXJBdHRyaWJ1dGVzIGV4dGVuZHMgRnJhbWVMYXlvdXRBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFRvZ2dsZUJ1dHRvbkF0dHJpYnV0ZXMgZXh0ZW5kcyBDb21wb3VuZEJ1dHRvbkF0dHJpYnV0ZXMge1xuICAgIHRleHRPZmY/OiBzdHJpbmc7XG4gICAgdGV4dE9uPzogc3RyaW5nO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVG9vbGJhckF0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBBdHRyaWJ1dGVzIHtcbiAgICBjb250ZW50SW5zZXRFbmQ/OiBzdHJpbmc7XG4gICAgY29udGVudEluc2V0RW5kV2l0aEFjdGlvbnM/OiBzdHJpbmc7XG4gICAgY29udGVudEluc2V0TGVmdD86IHN0cmluZztcbiAgICBjb250ZW50SW5zZXRSaWdodD86IHN0cmluZztcbiAgICBjb250ZW50SW5zZXRTdGFydD86IHN0cmluZztcbiAgICBjb250ZW50SW5zZXRTdGFydFdpdGhOYXZpZ2F0aW9uPzogc3RyaW5nO1xuICAgIGxvZ28/OiBudW1iZXI7XG4gICAgbG9nb0Rlc2NyaXB0aW9uPzogc3RyaW5nO1xuICAgIG5hdmlnYXRpb25Db250ZW50RGVzY3JpcHRpb24/OiBzdHJpbmc7XG4gICAgbmF2aWdhdGlvbkljb24/OiBzdHJpbmc7XG4gICAgcG9wdXBUaGVtZT86IG51bWJlcjtcbiAgICBzdWJ0aXRsZT86IHN0cmluZztcbiAgICBzdWJ0aXRsZVRleHRDb2xvcj86IHN0cmluZztcbiAgICB0aXRsZT86IHN0cmluZztcbiAgICB0aXRsZU1hcmdpbj86IHN0cmluZztcbiAgICB0aXRsZU1hcmdpbkJvdHRvbT86IHN0cmluZztcbiAgICB0aXRsZU1hcmdpbkVuZD86IHN0cmluZztcbiAgICB0aXRsZU1hcmdpblN0YXJ0Pzogc3RyaW5nO1xuICAgIHRpdGxlTWFyZ2luVG9wPzogc3RyaW5nO1xuICAgIHRpdGxlVGV4dENvbG9yPzogc3RyaW5nO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVG9vbGJhckxheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgZW51bSBUcmFja1RpbnRNb2RlRW51bSB7XG4gICAgYWRkID0gJ2FkZCcsXG4gICAgbXVsdGlwbHkgPSAnbXVsdGlwbHknLFxuICAgIHNjcmVlbiA9ICdzY3JlZW4nLFxuICAgIHNyY19hdG9wID0gJ3NyY19hdG9wJyxcbiAgICBzcmNfaW4gPSAnc3JjX2luJyxcbiAgICBzcmNfb3ZlciA9ICdzcmNfb3ZlcidcbiAgfVxuICBleHBvcnQgZW51bSBUcmFuc2NyaXB0TW9kZUVudW0ge1xuICAgIGFsd2F5c1Njcm9sbCA9ICdhbHdheXNTY3JvbGwnLFxuICAgIGRpc2FibGVkID0gJ2Rpc2FibGVkJyxcbiAgICBub3JtYWwgPSAnbm9ybWFsJ1xuICB9XG4gIGV4cG9ydCBlbnVtIFZlcnRpY2FsU2Nyb2xsYmFyUG9zaXRpb25FbnVtIHtcbiAgICBkZWZhdWx0UG9zaXRpb24gPSAnZGVmYXVsdFBvc2l0aW9uJyxcbiAgICBsZWZ0ID0gJ2xlZnQnLFxuICAgIHJpZ2h0ID0gJ3JpZ2h0J1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVmlkZW9WaWV3QXR0cmlidXRlcyBleHRlbmRzIFN1cmZhY2VWaWV3QXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBWaWV3QW5pbWF0b3JBdHRyaWJ1dGVzIGV4dGVuZHMgRnJhbWVMYXlvdXRBdHRyaWJ1dGVzIHtcbiAgICBGcmFtZUxheW91dF9tZWFzdXJlQWxsQ2hpbGRyZW4/OiBib29sZWFuO1xuICAgIGFuaW1hdGVGaXJzdFZpZXc/OiBib29sZWFuO1xuICAgIGluQW5pbWF0aW9uPzogc3RyaW5nO1xuICAgIG91dEFuaW1hdGlvbj86IHN0cmluZztcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFZpZXdBdHRyaWJ1dGVzIHtcbiAgICBhY2Nlc3NpYmlsaXR5SGVhZGluZz86IGJvb2xlYW47XG4gICAgYWNjZXNzaWJpbGl0eUxpdmVSZWdpb24/OiBudW1iZXIgfCBBY2Nlc3NpYmlsaXR5TGl2ZVJlZ2lvbkVudW07XG4gICAgYWNjZXNzaWJpbGl0eVBhbmVUaXRsZT86IHN0cmluZztcbiAgICBhY2Nlc3NpYmlsaXR5VHJhdmVyc2FsQWZ0ZXI/OiBudW1iZXI7XG4gICAgYWNjZXNzaWJpbGl0eVRyYXZlcnNhbEJlZm9yZT86IG51bWJlcjtcbiAgICBhbHBoYT86IG51bWJlcjtcbiAgICBhdXRvZmlsbEhpbnRzPzogc3RyaW5nO1xuICAgIGJhY2tncm91bmQ/OiBzdHJpbmc7XG4gICAgYmFja2dyb3VuZFRpbnQ/OiBzdHJpbmc7XG4gICAgYmFja2dyb3VuZFRpbnRNb2RlPzogQmFja2dyb3VuZFRpbnRNb2RlRW51bTtcbiAgICBjbGlja2FibGU/OiBib29sZWFuO1xuICAgIGNvbnRlbnREZXNjcmlwdGlvbj86IHN0cmluZztcbiAgICBjb250ZXh0Q2xpY2thYmxlPzogYm9vbGVhbjtcbiAgICBkZWZhdWx0Rm9jdXNIaWdobGlnaHRFbmFibGVkPzogYm9vbGVhbjtcbiAgICBkcmF3aW5nQ2FjaGVRdWFsaXR5PzogRHJhd2luZ0NhY2hlUXVhbGl0eUVudW07XG4gICAgZWxldmF0aW9uPzogc3RyaW5nO1xuICAgIGZhZGVTY3JvbGxiYXJzPzogYm9vbGVhbjtcbiAgICBmaWx0ZXJUb3VjaGVzV2hlbk9ic2N1cmVkPzogYm9vbGVhbjtcbiAgICBmaXRzU3lzdGVtV2luZG93cz86IGJvb2xlYW47XG4gICAgZm9jdXNhYmxlPzogYm9vbGVhbiB8IEZvY3VzYWJsZUVudW07XG4gICAgZm9jdXNhYmxlSW5Ub3VjaE1vZGU/OiBib29sZWFuO1xuICAgIGZvY3VzZWRCeURlZmF1bHQ/OiBib29sZWFuO1xuICAgIGZvcmNlSGFzT3ZlcmxhcHBpbmdSZW5kZXJpbmc/OiBib29sZWFuO1xuICAgIGZvcmVncm91bmQ/OiBzdHJpbmc7XG4gICAgZm9yZWdyb3VuZEdyYXZpdHk/OiBGb3JlZ3JvdW5kR3Jhdml0eUZsYWdzRW51bVtdO1xuICAgIGZvcmVncm91bmRUaW50Pzogc3RyaW5nO1xuICAgIGZvcmVncm91bmRUaW50TW9kZT86IEZvcmVncm91bmRUaW50TW9kZUVudW07XG4gICAgaGFwdGljRmVlZGJhY2tFbmFibGVkPzogYm9vbGVhbjtcbiAgICBpZD86IHN0cmluZztcbiAgICBpbXBvcnRhbnRGb3JBY2Nlc3NpYmlsaXR5PzogbnVtYmVyIHwgSW1wb3J0YW50Rm9yQWNjZXNzaWJpbGl0eUVudW07XG4gICAgaW1wb3J0YW50Rm9yQXV0b2ZpbGw/OiBJbXBvcnRhbnRGb3JBdXRvZmlsbEZsYWdzRW51bVtdO1xuICAgIGlzU2Nyb2xsQ29udGFpbmVyPzogYm9vbGVhbjtcbiAgICBrZWVwU2NyZWVuT24/OiBib29sZWFuO1xuICAgIGtleWJvYXJkTmF2aWdhdGlvbkNsdXN0ZXI/OiBib29sZWFuO1xuICAgIGxhYmVsRm9yPzogc3RyaW5nO1xuICAgIGxheWVyVHlwZT86IExheWVyVHlwZUVudW07XG4gICAgbGF5b3V0RGlyZWN0aW9uPzogTGF5b3V0RGlyZWN0aW9uRW51bTtcbiAgICBsb25nQ2xpY2thYmxlPzogYm9vbGVhbjtcbiAgICBtaW5IZWlnaHQ/OiBzdHJpbmc7XG4gICAgbWluV2lkdGg/OiBzdHJpbmc7XG4gICAgbmVzdGVkU2Nyb2xsaW5nRW5hYmxlZD86IGJvb2xlYW47XG4gICAgbmV4dENsdXN0ZXJGb3J3YXJkPzogc3RyaW5nO1xuICAgIG5leHRGb2N1c0Rvd24/OiBzdHJpbmc7XG4gICAgbmV4dEZvY3VzRm9yd2FyZD86IHN0cmluZztcbiAgICBuZXh0Rm9jdXNMZWZ0Pzogc3RyaW5nO1xuICAgIG5leHRGb2N1c1JpZ2h0Pzogc3RyaW5nO1xuICAgIG5leHRGb2N1c1VwPzogc3RyaW5nO1xuICAgIG91dGxpbmVBbWJpZW50U2hhZG93Q29sb3I/OiBzdHJpbmc7XG4gICAgb3V0bGluZVNwb3RTaGFkb3dDb2xvcj86IHN0cmluZztcbiAgICBvdmVyU2Nyb2xsTW9kZT86IE92ZXJTY3JvbGxNb2RlRW51bTtcbiAgICBwYWRkaW5nQm90dG9tPzogc3RyaW5nO1xuICAgIHBhZGRpbmdFbmQ/OiBzdHJpbmc7XG4gICAgcGFkZGluZ0xlZnQ/OiBzdHJpbmc7XG4gICAgcGFkZGluZ1JpZ2h0Pzogc3RyaW5nO1xuICAgIHBhZGRpbmdTdGFydD86IHN0cmluZztcbiAgICBwYWRkaW5nVG9wPzogc3RyaW5nO1xuICAgIHJvdGF0aW9uPzogbnVtYmVyO1xuICAgIHJvdGF0aW9uWD86IG51bWJlcjtcbiAgICByb3RhdGlvblk/OiBudW1iZXI7XG4gICAgc2F2ZUVuYWJsZWQ/OiBib29sZWFuO1xuICAgIHNjYWxlWD86IG51bWJlcjtcbiAgICBzY2FsZVk/OiBudW1iZXI7XG4gICAgc2NyZWVuUmVhZGVyRm9jdXNhYmxlPzogYm9vbGVhbjtcbiAgICBzY3JvbGxJbmRpY2F0b3JzPzogU2Nyb2xsSW5kaWNhdG9yc0ZsYWdzRW51bVtdO1xuICAgIHNjcm9sbFg/OiBzdHJpbmc7XG4gICAgc2Nyb2xsWT86IHN0cmluZztcbiAgICBzY3JvbGxiYXJEZWZhdWx0RGVsYXlCZWZvcmVGYWRlPzogbnVtYmVyO1xuICAgIHNjcm9sbGJhckZhZGVEdXJhdGlvbj86IG51bWJlcjtcbiAgICBzY3JvbGxiYXJTaXplPzogc3RyaW5nO1xuICAgIHNjcm9sbGJhclN0eWxlPzogU2Nyb2xsYmFyU3R5bGVFbnVtO1xuICAgIHNvdW5kRWZmZWN0c0VuYWJsZWQ/OiBib29sZWFuO1xuICAgIHRhZz86IHN0cmluZztcbiAgICB0ZXh0QWxpZ25tZW50PzogbnVtYmVyIHwgVGV4dEFsaWdubWVudEVudW07XG4gICAgdGV4dERpcmVjdGlvbj86IG51bWJlciB8IFRleHREaXJlY3Rpb25FbnVtO1xuICAgIHRvb2x0aXBUZXh0Pzogc3RyaW5nO1xuICAgIHRyYW5zZm9ybVBpdm90WD86IHN0cmluZztcbiAgICB0cmFuc2Zvcm1QaXZvdFk/OiBzdHJpbmc7XG4gICAgdHJhbnNpdGlvbk5hbWU/OiBzdHJpbmc7XG4gICAgdHJhbnNsYXRpb25YPzogc3RyaW5nO1xuICAgIHRyYW5zbGF0aW9uWT86IHN0cmluZztcbiAgICB0cmFuc2xhdGlvblo/OiBzdHJpbmc7XG4gICAgdmVydGljYWxTY3JvbGxiYXJQb3NpdGlvbj86IFZlcnRpY2FsU2Nyb2xsYmFyUG9zaXRpb25FbnVtO1xuICAgIHZpc2liaWxpdHk/OiBWaXNpYmlsaXR5RW51bTtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFZpZXdGbGlwcGVyQXR0cmlidXRlcyBleHRlbmRzIFZpZXdBbmltYXRvckF0dHJpYnV0ZXMge1xuICAgIGF1dG9TdGFydD86IGJvb2xlYW47XG4gICAgZmxpcEludGVydmFsPzogbnVtYmVyO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVmlld0dyb3VwQXR0cmlidXRlcyBleHRlbmRzIFZpZXdBdHRyaWJ1dGVzIHtcbiAgICBhZGRTdGF0ZXNGcm9tQ2hpbGRyZW4/OiBib29sZWFuO1xuICAgIGFsd2F5c0RyYXduV2l0aENhY2hlPzogYm9vbGVhbjtcbiAgICBhbmltYXRlTGF5b3V0Q2hhbmdlcz86IGJvb2xlYW47XG4gICAgYW5pbWF0aW9uQ2FjaGU/OiBib29sZWFuO1xuICAgIGNsaXBDaGlsZHJlbj86IGJvb2xlYW47XG4gICAgY2xpcFRvUGFkZGluZz86IGJvb2xlYW47XG4gICAgbGF5b3V0TW9kZT86IExheW91dE1vZGVFbnVtO1xuICAgIHBlcnNpc3RlbnREcmF3aW5nQ2FjaGU/OiBQZXJzaXN0ZW50RHJhd2luZ0NhY2hlRmxhZ3NFbnVtW107XG4gICAgc3BsaXRNb3Rpb25FdmVudHM/OiBib29sZWFuO1xuICAgIHRvdWNoc2NyZWVuQmxvY2tzRm9jdXM/OiBib29sZWFuO1xuICAgIHRyYW5zaXRpb25Hcm91cD86IGJvb2xlYW47XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBWaWV3R3JvdXBMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgICBsYXlvdXRfaGVpZ2h0Pzogc3RyaW5nIHwgTGF5b3V0SGVpZ2h0RW51bTtcbiAgICBsYXlvdXRfd2lkdGg/OiBzdHJpbmcgfCBMYXlvdXRXaWR0aEVudW07XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBWaWV3R3JvdXBNYXJnaW5MYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0dyb3VwTGF5b3V0UGFyYW1zQXR0cmlidXRlcyB7XG4gICAgbGF5b3V0X21hcmdpbkJvdHRvbT86IHN0cmluZztcbiAgICBsYXlvdXRfbWFyZ2luRW5kPzogc3RyaW5nO1xuICAgIGxheW91dF9tYXJnaW5MZWZ0Pzogc3RyaW5nO1xuICAgIGxheW91dF9tYXJnaW5SaWdodD86IHN0cmluZztcbiAgICBsYXlvdXRfbWFyZ2luU3RhcnQ/OiBzdHJpbmc7XG4gICAgbGF5b3V0X21hcmdpblRvcD86IHN0cmluZztcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFZpZXdTdHViQXR0cmlidXRlcyBleHRlbmRzIFZpZXdBdHRyaWJ1dGVzIHtcbiAgICBpbmZsYXRlZElkPzogc3RyaW5nO1xuICAgIGxheW91dD86IHN0cmluZztcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFZpZXdTd2l0Y2hlckF0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3QW5pbWF0b3JBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgZW51bSBWaXNpYmlsaXR5RW51bSB7XG4gICAgZ29uZSA9ICdnb25lJyxcbiAgICBpbnZpc2libGUgPSAnaW52aXNpYmxlJyxcbiAgICB2aXNpYmxlID0gJ3Zpc2libGUnXG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBab29tQ29udHJvbHNBdHRyaWJ1dGVzIGV4dGVuZHMgTGluZWFyTGF5b3V0QXR0cmlidXRlcyB7XG4gIH1cbn1cbi8vIGVsZW1lbnRzXG5leHBvcnQgY29uc3QgQWRhcHRlclZpZXdBbmltYXRvciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5BZGFwdGVyVmlld0FuaW1hdG9yQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkFkYXB0ZXJWaWV3QW5pbWF0b3JBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2FkYXB0ZXJWaWV3QW5pbWF0b3InLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEZyYW1lTGF5b3V0ID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkZyYW1lTGF5b3V0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkZyYW1lTGF5b3V0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdmcmFtZUxheW91dCcsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5WaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlZpZXdBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3ZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFNlYXJjaFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuU2VhcmNoVmlld0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5TZWFyY2hWaWV3QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdzZWFyY2hWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBUYWJsZVJvdyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5UYWJsZVJvd0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5UYWJsZVJvd0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgndGFibGVSb3cnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFJlbGF0aXZlTGF5b3V0ID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlJlbGF0aXZlTGF5b3V0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlJlbGF0aXZlTGF5b3V0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdyZWxhdGl2ZUxheW91dCcsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgSW1hZ2VTd2l0Y2hlciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5JbWFnZVN3aXRjaGVyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkltYWdlU3dpdGNoZXJBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2ltYWdlU3dpdGNoZXInLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFJhZGlvR3JvdXAgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuUmFkaW9Hcm91cEF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5SYWRpb0dyb3VwQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdyYWRpb0dyb3VwJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBUb29sYmFyID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlRvb2xiYXJBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuVG9vbGJhckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgndG9vbGJhcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgTGlzdFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuTGlzdFZpZXdBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuTGlzdFZpZXdBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2xpc3RWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBTcGlubmVyID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlNwaW5uZXJBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuU3Bpbm5lckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnc3Bpbm5lcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgU2Nyb2xsVmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5TY3JvbGxWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlNjcm9sbFZpZXdBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3Njcm9sbFZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFZpZXdGbGlwcGVyID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlZpZXdGbGlwcGVyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlZpZXdGbGlwcGVyQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCd2aWV3RmxpcHBlcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVmlld1N3aXRjaGVyID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlZpZXdTd2l0Y2hlckF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5WaWV3U3dpdGNoZXJBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3ZpZXdTd2l0Y2hlcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgRGF0ZVBpY2tlciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5EYXRlUGlja2VyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkRhdGVQaWNrZXJBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2RhdGVQaWNrZXInLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFN0YWNrVmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5TdGFja1ZpZXdBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuU3RhY2tWaWV3QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdzdGFja1ZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFRpbWVQaWNrZXIgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuVGltZVBpY2tlckF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5UaW1lUGlja2VyQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCd0aW1lUGlja2VyJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBNZWRpYUNvbnRyb2xsZXIgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuTWVkaWFDb250cm9sbGVyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLk1lZGlhQ29udHJvbGxlckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnbWVkaWFDb250cm9sbGVyJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBBY3Rpb25NZW51VmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5BY3Rpb25NZW51Vmlld0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5BY3Rpb25NZW51Vmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnYWN0aW9uTWVudVZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFpvb21Db250cm9scyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5ab29tQ29udHJvbHNBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuWm9vbUNvbnRyb2xzQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCd6b29tQ29udHJvbHMnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEFkYXB0ZXJWaWV3ID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkFkYXB0ZXJWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkFkYXB0ZXJWaWV3QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdhZGFwdGVyVmlldycsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVmlld0FuaW1hdG9yID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlZpZXdBbmltYXRvckF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5WaWV3QW5pbWF0b3JBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3ZpZXdBbmltYXRvcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgSG9yaXpvbnRhbFNjcm9sbFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuSG9yaXpvbnRhbFNjcm9sbFZpZXdBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuSG9yaXpvbnRhbFNjcm9sbFZpZXdBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2hvcml6b250YWxTY3JvbGxWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBDYWxlbmRhclZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuQ2FsZW5kYXJWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkNhbGVuZGFyVmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnY2FsZW5kYXJWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBBYnNMaXN0VmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5BYnNMaXN0Vmlld0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5BYnNMaXN0Vmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnYWJzTGlzdFZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IExpbmVhckxheW91dCA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5MaW5lYXJMYXlvdXRBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuTGluZWFyTGF5b3V0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdsaW5lYXJMYXlvdXQnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IE51bWJlclBpY2tlciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5OdW1iZXJQaWNrZXJBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuTnVtYmVyUGlja2VyQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdudW1iZXJQaWNrZXInLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEFkYXB0ZXJWaWV3RmxpcHBlciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5BZGFwdGVyVmlld0ZsaXBwZXJBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuQWRhcHRlclZpZXdGbGlwcGVyQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdhZGFwdGVyVmlld0ZsaXBwZXInLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEFic1NwaW5uZXIgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuQWJzU3Bpbm5lckF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5BYnNTcGlubmVyQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdhYnNTcGlubmVyJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBWaWV3R3JvdXAgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuVmlld0dyb3VwQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlZpZXdHcm91cEF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgndmlld0dyb3VwJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBUZXh0U3dpdGNoZXIgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuVGV4dFN3aXRjaGVyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlRleHRTd2l0Y2hlckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgndGV4dFN3aXRjaGVyJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBFeHBhbmRhYmxlTGlzdFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuRXhwYW5kYWJsZUxpc3RWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkV4cGFuZGFibGVMaXN0Vmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnZXhwYW5kYWJsZUxpc3RWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBHcmlkVmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5HcmlkVmlld0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5HcmlkVmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnZ3JpZFZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFRhYmxlTGF5b3V0ID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlRhYmxlTGF5b3V0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlRhYmxlTGF5b3V0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCd0YWJsZUxheW91dCcsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVGFiSG9zdCA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5UYWJIb3N0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlRhYkhvc3RBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3RhYkhvc3QnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEdyaWRMYXlvdXQgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuR3JpZExheW91dEF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5HcmlkTGF5b3V0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdncmlkTGF5b3V0JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBUYWJXaWRnZXQgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuVGFiV2lkZ2V0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlRhYldpZGdldEF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgndGFiV2lkZ2V0JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBTcGFjZSA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5TcGFjZUF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5TcGFjZUF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnc3BhY2UnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IE11bHRpQXV0b0NvbXBsZXRlVGV4dFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuTXVsdGlBdXRvQ29tcGxldGVUZXh0Vmlld0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5NdWx0aUF1dG9Db21wbGV0ZVRleHRWaWV3QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdtdWx0aUF1dG9Db21wbGV0ZVRleHRWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBRdWlja0NvbnRhY3RCYWRnZSA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5RdWlja0NvbnRhY3RCYWRnZUF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5RdWlja0NvbnRhY3RCYWRnZUF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgncXVpY2tDb250YWN0QmFkZ2UnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEVkaXRUZXh0ID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkVkaXRUZXh0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkVkaXRUZXh0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdlZGl0VGV4dCcsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgU3VyZmFjZVZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuU3VyZmFjZVZpZXdBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuU3VyZmFjZVZpZXdBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3N1cmZhY2VWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBSYXRpbmdCYXIgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuUmF0aW5nQmFyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlJhdGluZ0JhckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgncmF0aW5nQmFyJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBJbWFnZVZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuSW1hZ2VWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkltYWdlVmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnaW1hZ2VWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBBYnNTZWVrQmFyID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkFic1NlZWtCYXJBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuQWJzU2Vla0JhckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnYWJzU2Vla0JhcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVmlld1N0dWIgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuVmlld1N0dWJBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuVmlld1N0dWJBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3ZpZXdTdHViJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBDaGVja2VkVGV4dFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuQ2hlY2tlZFRleHRWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkNoZWNrZWRUZXh0Vmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnY2hlY2tlZFRleHRWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBUZXh0dXJlVmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5UZXh0dXJlVmlld0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5UZXh0dXJlVmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgndGV4dHVyZVZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFByb2dyZXNzQmFyID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlByb2dyZXNzQmFyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlByb2dyZXNzQmFyQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdwcm9ncmVzc0JhcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVGV4dFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuVGV4dFZpZXdBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuVGV4dFZpZXdBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3RleHRWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBDaGVja0JveCA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5DaGVja0JveEF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5DaGVja0JveEF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnY2hlY2tCb3gnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFN3aXRjaCA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5Td2l0Y2hBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuU3dpdGNoQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdzd2l0Y2gnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFJhZGlvQnV0dG9uID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlJhZGlvQnV0dG9uQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlJhZGlvQnV0dG9uQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdyYWRpb0J1dHRvbicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgU2Vla0JhciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5TZWVrQmFyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlNlZWtCYXJBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3NlZWtCYXInLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IENvbXBvdW5kQnV0dG9uID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkNvbXBvdW5kQnV0dG9uQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkNvbXBvdW5kQnV0dG9uQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdjb21wb3VuZEJ1dHRvbicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVG9nZ2xlQnV0dG9uID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlRvZ2dsZUJ1dHRvbkF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5Ub2dnbGVCdXR0b25BdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3RvZ2dsZUJ1dHRvbicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVGV4dENsb2NrID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlRleHRDbG9ja0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5UZXh0Q2xvY2tBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3RleHRDbG9jaycsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgSW1hZ2VCdXR0b24gPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuSW1hZ2VCdXR0b25BdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuSW1hZ2VCdXR0b25BdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2ltYWdlQnV0dG9uJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBDaHJvbm9tZXRlciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5DaHJvbm9tZXRlckF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5DaHJvbm9tZXRlckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnY2hyb25vbWV0ZXInLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFZpZGVvVmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5WaWRlb1ZpZXdBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuVmlkZW9WaWV3QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCd2aWRlb1ZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEF1dG9Db21wbGV0ZVRleHRWaWV3ID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkF1dG9Db21wbGV0ZVRleHRWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkF1dG9Db21wbGV0ZVRleHRWaWV3QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdhdXRvQ29tcGxldGVUZXh0VmlldycsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgQnV0dG9uID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkJ1dHRvbkF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5CdXR0b25BdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2J1dHRvbicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07Il0sInZlcnNpb24iOjN9